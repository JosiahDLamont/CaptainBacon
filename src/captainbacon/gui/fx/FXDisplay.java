package captainbacon.gui.fx;

import captainbacon.gui.IDisplay;
import captainbacon.gui.render.ILayer;
import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JavaFX implementation of IDisplay. Made to work with the FXLayer implementation of
 * ILayer. Allows the prioritized, repeated drawing of layers to a JavaFX Stage.
 */
class FXDisplay implements IDisplay {

    private final Stage stage;

    // Stores the render layers in ascending priority.
    private final List<FXLayer> layers;

    private final Lock displayLock = new ReentrantLock();

    /**
     * @param stage is the JavaFX stage to manage with this FXDisplay.
     */
    FXDisplay(@NotNull Stage stage) {
        this.stage = stage;
        layers = new LinkedList<>();
    }

    @Override
    public void update(@NotNull ILayer layer) {
        displayLock.lock();

        FXLayer fxLayer = (FXLayer) layer;

        if (layers.isEmpty()) {
            // No layers have been added yet; add this one.
            layers.add(fxLayer);
        }

        // Place layer in its proper position in layers.
        ListIterator<FXLayer> iter = layers.listIterator();
        while (iter.hasNext()) {
            int nextPriority = iter.next().priority;

            if (fxLayer.priority == nextPriority) {
                // The given render layer already exists; replace it.
                iter.set(fxLayer);

                displayLock.unlock();
                return;
            } else if (fxLayer.priority < nextPriority) {
                // The given render layer is of lower priority than the next layer.
                iter.previous();
                iter.add(fxLayer);

                displayLock.unlock();
                return;
            }
        }
        // The given render layer has a higher priority than any existing layers.
        layers.add(fxLayer);

        displayLock.unlock();
    }


    @Override
    public void draw() {
        // Tell the FX Thread to run the private method drawFX as soon as it has time to do so.
        Platform.runLater(this::drawFX);
    }

    /**
     * This method performs the actual addition of the Layers to stage in the FX Thread.
     */
    private void drawFX() {
        displayLock.lock();

        Group canvases = new Group();
        canvases.getChildren().addAll(layers);

        Scene scene = new Scene(canvases, 1000, 500, Color.BLACK);
        stage.setScene(scene);

        displayLock.unlock();
    }
}

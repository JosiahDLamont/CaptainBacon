package captainbacon.gui.fx;

import captainbacon.gui.IDisplay;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * JavaFX implementation of IDisplay. Made to work with an implementation of
 * ILayer that extends the JavaFX Canvas class.
 */
public class Display implements IDisplay {

    private final Stage stage;
    private final List<Layer> layers;

    public Display(Stage stage) {
        this.stage = stage;
        layers = new LinkedList<>();
    }

    @Override
    public void update(Layer layer) {
        ListIterator<Layer> iter = layers.listIterator();
        while (iter.hasNext()) {
            int nextPriority = iter.next().priority;

            if (layer.priority == nextPriority) {
                iter.set(layer);
                break;
            } else if (layer.priority < nextPriority) {
                iter.previous();
                iter.add(layer);
                break;
            }
        }
    }

    @Override
    public void draw() {
        Group canvases = new Group();
        canvases.getChildren().addAll(layers);

        Scene scene = new Scene(canvases);
        stage.setScene(scene);
    }
}

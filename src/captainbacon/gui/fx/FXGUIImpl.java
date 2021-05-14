package captainbacon.gui.fx;

import captainbacon.gui.IDisplay;
import captainbacon.gui.IGUIImpl;
import captainbacon.gui.render.ILayer;
import captainbacon.gui.render.ITexture;
import com.sun.istack.internal.NotNull;
import javafx.stage.Stage;

import java.io.File;

/**
 * A GUI implementation using JavaFX.
 *
 * Correspondences:
 *      GUI_Impl = [JavaFX]
 */
public class FXGUIImpl implements IGUIImpl {

    private final FXDisplay display;

    /**
     * @param stage is the primary Stage for the program.
     */
    public FXGUIImpl(@NotNull Stage stage) {
        display = new FXDisplay(stage);
    }

    @Override
    public IDisplay getDisplay() {
        return display;
    }

    @Override
    public ILayer generateLayer(int priority, int width, int height) {
        return new FXLayer(priority, width, height);
    }

    @Override
    public ITexture loadTexture(File imageFile) {//TODO: add single file handler in io/file?
        //Throws IllegalArgumentException when invalid URL. TODO: add error handling, default texture (Renderer?), and log message.
        return new FXTexture(imageFile.toURI().toString());
    }
}

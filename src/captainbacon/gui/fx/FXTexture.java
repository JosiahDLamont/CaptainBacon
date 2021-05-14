package captainbacon.gui.fx;

import captainbacon.gui.render.ITexture;
import javafx.scene.image.Image;

/**
 * JavaFX implementation of ITexture wrapping the Image class.
 */
public class FXTexture extends Image implements ITexture {
    public FXTexture(String url) {
        super(url);
    }
}

package captainbacon.gui;

import captainbacon.gui.render.ILayer;
import captainbacon.gui.render.ITexture;

import java.io.File;

/**
 * @author Philip Cooper
 * This interface outlines a set of factory methods to give the Renderer a single access
 * point to the classes of the GUI implementation it is using.
 *
 * Defines:
 *      GUI_Impl: [a given implementation method for the game GUI]
 *
 * Constraints:
 *      [GUI_Impl is constant.]
 */
public interface IGUIImpl {

    /**
     * @return an instance of IDisplay using the given GUI_Impl.
     */
    IDisplay getDisplay();

    /**
     * @param priority is the layer priority of the layer.
     * @param width is the pixel width of the layer.
     * @param height is the pixel height of the layer.
     *
     * @return an ILayer with the given priority, width, and height using the given GUI_Impl.
     */
    ILayer generateLayer(int priority, int width, int height);


    /**
     * Loads a texture from the given file.
     *
     * @param imageFile is the file to load as a texture.
     * @return an ITexture with the contents of the given file using the given GUI_Impl.//TODO: add error return val
     */
    ITexture loadTexture(File imageFile);

    /**
     * Loads a texture from the given path.
     *
     * @param imagePath is the path of the image file to load.
     * @return an ITexture with the contents of the given file using the given GUI_Impl.//TODO: add error return val
     */
    default ITexture loadTexture(String imagePath) {
        return loadTexture(new File(imagePath));
    }
}

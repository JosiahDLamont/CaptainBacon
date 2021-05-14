package captainbacon.gui;

import captainbacon.gui.render.ILayer;

/**
 * This interface defines the basic functionality of a display suited for animations.
 *
 * Defines:
 *      Window: [the screen on which to draw]
 *      Layers: [a list of ILayer objects to draw to Window]
 *
 * Constraints:
 *      [Window is constant.]
 *
 * Initialization Ensures:
 *      Window != null
 */
public interface IDisplay {

    /**
     * Updates or adds the given ILayer in Layers.
     *
     * @param layer is the ILayer with which to update Layers.
     */
    void update(ILayer layer);

    /**
     * Draws the contents of Layers to Window in order of priority (lowest to highest).
     */
    void draw();
}

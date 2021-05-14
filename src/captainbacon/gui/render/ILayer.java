package captainbacon.gui.render;

/**
 * Defines a render layer that can be drawn on and drawn to an IDisplay.
 */
public interface ILayer {
    int BACKGROUND = 0;
    int TERRAIN = 500;
    int SURFACE_OBJECTS = 1000;
    int HUD = 1500;
    int MENU = 2000;

    /**
     * Draws the given texture to layer at pixel position (x,y) with the given pixel width and height.
     * Note that pixel positions are measured from the top left corner of the screen.
     *
     * @param texture is the ITexture to draw.
     * @param x is the x pixel position at which to draw the texture.
     * @param y is the y pixel position at which to draw the texture.
     * @param width is the pixel width to use for drawing the texture (scaling if different from the original width).
     * @param height is the pixel height to use for drawing the texture (scaling if different from the original height).
     */
    void drawTexture(ITexture texture, int x, int y, int width, int height);
}

package captainbacon.gui.fx;

import captainbacon.gui.render.ILayer;
import captainbacon.gui.render.ITexture;
import javafx.scene.canvas.Canvas;

/**
 * Contains a canvas for rendering paired with a render layer priority.
 */
class FXLayer extends Canvas implements ILayer {

    public final int priority;

    /**
     * @param priority is the type of layer this is and determines its draw priority.
     * @param width is the pixel width of the layer.
     * @param height is the pixel height of the layer.
     */
    FXLayer(int priority, int width, int height) {
        super(width, height);
        this.priority = priority;
    }

    @Override
    public void drawTexture(ITexture texture, int x, int y, int width, int height) {
        getGraphicsContext2D().drawImage((FXTexture) texture, x, y, width, height);
    }
}

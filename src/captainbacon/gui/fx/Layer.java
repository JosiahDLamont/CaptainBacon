package captainbacon.gui.fx;

import captainbacon.gui.ILayer;
import javafx.scene.canvas.Canvas;

/**
 * Contains a canvas for rendering paired with a render layer priority.
 */
public class Layer extends Canvas implements ILayer {

    public final int priority;

    public Layer(int priority) {
        this.priority = priority;
    }
}

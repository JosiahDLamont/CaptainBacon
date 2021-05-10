package captainbacon.gui.render;

import captainbacon.util.TimedLoop;

public class Renderer extends TimedLoop {

    /**
     * Constructs a Renderer with the given tick rate. //TODO: add args for display size/graphics properties
     *
     * @pre tickRate > 0
     * @post this.tickRate = tickRate
     * @post realTickRate = tickRate
     *
     * @param tickRate is the number of times to run the Renderer loop per second.
     */
    public Renderer(int tickRate) {
        super(tickRate);
    }

    @Override
    protected void doLoopAction() {

    }
}

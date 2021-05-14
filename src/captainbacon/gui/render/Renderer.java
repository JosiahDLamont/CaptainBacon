package captainbacon.gui.render;

import captainbacon.gui.IDisplay;
import captainbacon.gui.IGUIImpl;
import captainbacon.util.TimedLoop;

public class Renderer extends TimedLoop {

    /**
     * @invarient [All other GUI interfaces are instantiated via guiImpl.]
     */

    private final IGUIImpl guiImpl;
    private final IDisplay display;

    //TODO: remove this test code
    private final ITexture captnBacon;
    private int baconX = 0;

    /**
     * Constructs a Renderer with the given tick rate. //TODO: add args for display size/graphics properties
     *
     * @pre tickRate > 0
     * @post this.tickRate = tickRate
     * @post realTickRate = tickRate
     *
     * @param tickRate is the number of times to run the Renderer loop per second.
     */
    public Renderer(int tickRate, IGUIImpl guiImpl) {
        super(tickRate);
        this.guiImpl = guiImpl;
        this.display = guiImpl.getDisplay();

        captnBacon = guiImpl.loadTexture("BaconCaptain.png");
    }

    @Override
    protected void doLoopAction() {
        //TODO: remove this test code when better code is available.
        ILayer entities = guiImpl.generateLayer(ILayer.SURFACE_OBJECTS, 1000, 500);
        entities.drawTexture(captnBacon, baconX++, 250, 100, 100);
        display.update(entities);
        display.draw();
    }
}

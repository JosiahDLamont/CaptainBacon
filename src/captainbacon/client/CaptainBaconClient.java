package captainbacon.client;

import captainbacon.Game;
import captainbacon.util.logging.Logger;

/**
 * This class is the primary controller on the client-side,
 * though it delegates many of its tasks to other controllers.
 *
 * The client runs its own thread and client-side game loop.
 */
public class CaptainBaconClient extends Game implements IClient {

    private static final String NAME = "Client";
    private static final int TICK_RATE = 60;

    public CaptainBaconClient(Logger logger) {
        super(logger, NAME, TICK_RATE);
    }

    @Override
    public void run() {

    }

    @Override
    public void handlePlayer() {

    }
}

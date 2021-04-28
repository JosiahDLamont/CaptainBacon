package captainbacon.server;

import captainbacon.Game;

/**
 * This class is the primary controller on the server-side
 * containing the main game loop and running most of the game logic.
 *
 * The game loop communicates and requests data from registered clients,
 * directly in a local game (and, theoretically through a client-wrapped
 * networking class in remote games).
 */
public class CaptainBaconServer extends Game implements IServer {

    private static final String NAME = "Server";
    private static final int TICK_RATE = 60;

    public CaptainBaconServer() {
        super(NAME, TICK_RATE);
    }

    @Override
    public void run() {

    }

    @Override
    public void handlePlayer() {

    }
}

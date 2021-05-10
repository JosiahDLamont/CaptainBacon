package captainbacon.launch;

import captainbacon.Game;
import captainbacon.util.logging.Logger;

/**
 * @author Philip Cooper
 * This class contains the main method and launches the controller and the view
 * according to the provided command line arguments.
 */
public class Main {
    public static final Logger LOGGER = new Logger();

    public static void main(String[] args) {

        /*
         * Parse arguments here.
         * Potential arguments:
         *      logging level
         *      cheats/dev tools
         *      seed
         *      ...
         */

        // Launch the logger here.
        LOGGER.start();

        // Creates the Captain Bacon client and starts the GUI.
        FXLauncher.launch(FXLauncher.class, args);
    }
}

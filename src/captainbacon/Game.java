package captainbacon;

import captainbacon.util.TimedLoop;
import captainbacon.util.logging.Logger;
import captainbacon.util.logging.Message;

/**
 * @author Philip Cooper
 * Defines some basic functionality required for a game thread.
 */
public class Game extends TimedLoop {

    /**
     * @invarient logger != null
     */

    private final Logger logger;
    private static final String INFO_SUBSRC = ".info";
    private static final String WARN_SUBSRC = ".warn";
    private static final String ERR_SUBSRC = ".err";

    /**
     * Constructs a game thread with the given logger and thread name.
     *
     * @pre logger != null
     * @pre name != null
     *
     * @post this.logger = logger
     *
     * @param logger is the logger to use as the game thread's logger.
     * @param name is the name for the game thread.
     * @param tickRate is the number of times to run the game loop per second.
     */
    public Game(Logger logger, String name, int tickRate) {
        super(tickRate);
        this.logger = logger;
        setName(name);
    }




    /**
     * This method should contain the logic to run during each game tick.
     * @post [The actions of one game tick have ended.]
     */
    public void doLoopAction() {
        // TODO - Make a game
    }

    /**
     * This method should process player controls and interactions for each game loop cycle.
     */
    protected void handlePlayer(){
        // TODO - Make a player
    }


    /**
     * Logs an information message in the game thread.
     *
     * @pre message != null
     * @post [message has been logged to logger as an info message.]
     *
     * @param message is the information message to log.
     */
    public void logInfoMessage(String message) {
        logger.logMessage(new Message(getName() + INFO_SUBSRC, message));
    }

    /**
     * Logs a warning message in the game thread.
     *
     * @pre message != null
     * @post [message has been logged to logger as a warn message.]
     *
     * @param message is the warning message to log.
     */
    public void logWarnMessage(String message) {
        logger.logMessage(new Message(getName() + WARN_SUBSRC, message, Message.HIGH_PRIORITY));
    }

    /**
     * Logs an error message in the game thread.
     *
     * @pre message != null
     * @post [message has been logged to logger as an err message.]
     *
     * @param message is the error message to log.
     */
    public void logErrMessage(String message) {
        logger.logMessage(new Message(getName() + ERR_SUBSRC, message, Message.MAX_PRIORITY));
    }
}

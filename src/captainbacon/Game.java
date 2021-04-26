package captainbacon;

import captainbacon.util.logging.Logger;
import captainbacon.util.logging.Message;

/**
 * @author Philip Cooper
 * Defines some basic functionality required for a game thread.
 */
public abstract class Game extends Thread {

    /**
     * @invarient logger != null
     */

    private final Logger logger;
    private static final String INFO_SUBSRC = ".info";
    private static final String WARN_SUBSRC = ".warn";
    private static final String ERR_SUBSRC = ".err";

    private final int tickRate;
    private float realTickRate;
    private long startTimeMillis;

    private boolean gameOver = false;

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
     */
    public Game(Logger logger, String name, int tickRate) {
        this.logger = logger;
        setName(name);
        this.tickRate = tickRate;
    }




    /**
     * This method should contain the game logic and loop.
     * @post [The Game has ended.]
     */
    @Override
    public void run() {

    }

    /**
     * This method should process player controls and interactions for each game loop cycle.
     */
    protected abstract void handlePlayer();

    /**
     * This method notifies the game that it should end as soon as possible.
     */
    public void endGame() {
        gameOver = true;
    }


    /**
     * @return the tick rate set in the program in ticks-per-second. This tick rate is not guaranteed
     *         in practice.
     */
    public int getTickRate() {
        return tickRate;
    }

    /**
     * @pre [endTick() has been correctly called at least once.]
     *
     * @return the effective tick rate at which the program completed the work of the most
     *         recent tick.
     */
    public float getRealTickRate() {
        return realTickRate;
    }

    /**
     * This method tells the game thread when a tick starts. Used for tick timing.
     *
     * @pre [There is a call to endTick() between any previous calls to startTick()
     *       and this call to startTick()]
     *
     * @post startTimeMillis = [the current system time]
     */
    protected void startTick() {
        startTimeMillis = System.currentTimeMillis();
    }

    /**
     * This method tells the game thread when a tick's work has finished. If the
     * tick ended with time to spare before the next tick, the thread sleeps through
     * the extra time. It also calculates the real tick rate.
     *
     * @pre [startTick() has been called and there are no other calls to endTick() between
     *       the call to startTick() and this call to endTick()]
     *
     * @post [The tick has lasted at least 1 / TICK_RATE seconds.]
     * @post realTickRate = [the actual rate at which a tick's work is executed]
     */
    protected void endTick() {
        long elapsedTime = System.currentTimeMillis() - startTimeMillis;

        // Calculate the real tick rate in seconds.
        realTickRate = 1000F / elapsedTime;

        // Determine if the elapsed time is less than the target milliseconds per tick.
        if (elapsedTime < 1000 / tickRate) {
            // There is extra time in the tick, try to sleep it off.
            try {
                sleep(1000 / tickRate - elapsedTime);
            } catch (InterruptedException e) {
                logErrMessage("Tick interrupted!");
            }
        }
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

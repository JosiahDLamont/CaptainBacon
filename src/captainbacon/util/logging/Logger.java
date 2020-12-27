package captainbacon.util.logging;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Philip Cooper
 * This class defines a logger that handles messages of varying priority in its own thread.
 */
public class Logger extends Thread {

    /**
     * @invarient Message.MIN_PRIORITY <= minPriority <= Message.MAX_PRIORITY
     * @invarient 0 <= cycleDelayMillis
     * @invarient messagesToLog != null
     * @invarient logOutput != null
     * @invarient loggerLock != null
     * @invarient [The logger thread name is either DEFAULT_NAME or user-provided.]
     */

    public static final String DEFAULT_NAME = "Logger";

    private static final int DEFAULT_CYCLE_DELAY = 1000;

    private int minPriority = Message.BASE_PRIORITY;
    private int cycleDelayMillis = DEFAULT_CYCLE_DELAY;
    private Queue<Message> messagesToLog = new ConcurrentLinkedQueue<>();

    private ILogOutput logOutput;

    private boolean isRunning;
    private Lock loggerLock = new ReentrantLock();

    /**
     * Constructs a Logger with a minimum priority of Message.BASE_PRIORITY
     *
     * @post [logOutput is a StandardLogOutput.]
     * @post [The thread name is the DEFAULT_NAME.]
     */
    public Logger() {
        logOutput = new StandardLogOutput();
        setName(DEFAULT_NAME);
    }

    /**
     * Constructs a Logger with a minimum priority of Message.BASE_PRIORITY
     *
     * @pre output != null
     * @pre name != null
     *
     * @post this.logOutput = logOutput
     * @post [The thread name is name.]
     *
     * @param logOutput is the ILogOutput implementation for outputting logs.
     * @param name is the name for the Logger thread.
     */
    public Logger(ILogOutput logOutput, String name) {
        this.logOutput = logOutput;
        setName(name);
    }

    /**
     * Runs the logger. The logger outputs messages every cycleDelayMillis milliseconds,
     * sleeping during those milliseconds to allow other threads to send messages to the
     * logger.
     */
    @Override
    public void run() {
        loggerLock.lock();
        isRunning = true;

        // Continue as long as the logger is running.
        while (isRunning) {
            loggerLock.unlock();

            // Print all messages accumulated in the cycle.
            while (!messagesToLog.isEmpty()) {
                Message message = messagesToLog.remove();
                logOutput.outputLog(message.getSource(), message.getMessage());
            }

            // Attempt to wait the specified cycle delay.
            try {
                sleep(cycleDelayMillis);
            } catch (InterruptedException e) {
                logMessage(new Message(getName(), "Log gathering cycle interrupted!", Message.MAX_PRIORITY));
            }

            loggerLock.lock();
        }

        loggerLock.unlock();
    }

    /**
     * This method provides the given message to the logger assuming it has sufficient priority.
     *
     * @pre message != null
     * @post [messagesToLog contains message] iff message.getPriority() >= minPriority
     *
     * @param message is the message to attempt to log.
     */
    public void logMessage(Message message) {
        if (message.getPriority() >= minPriority) {
            messagesToLog.add(message);
        }
    }

    /**
     * Tells the logger to stop running.
     *
     * @pre [None.]
     * @post isRunning = false
     */
    public void stopLogger() {
        loggerLock.lock();
        isRunning = false;
        loggerLock.unlock();
    }

    public int getMinPriority() {
        return minPriority;
    }

    public int getCycleDelay() {
        return cycleDelayMillis;
    }

    /**
     * @pre Message.MIN_PRIORITY <= priority <= Message.MAX_PRIORITY
     * @param priority is the minimum priority for messages the logger will log.
     */
    public void setMinPriority(int priority) {
        minPriority = priority;
    }

    /**
     * @pre 0 <= milliseconds
     * @param milliseconds is the number of milliseconds to collect logs before outputting them.
     */
    public void setCycleDelay(int milliseconds) {
        cycleDelayMillis = milliseconds;
    }
}
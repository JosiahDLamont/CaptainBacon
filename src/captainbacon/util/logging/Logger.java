package captainbacon.util.logging;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Philip Cooper
 * This class defines a logger that handles messages of varying types in its own thread.
 */
public class Logger extends Thread implements ILogger {

    /**
     * @invarient logLevel != null
     * @invarient 0 <= cycleDelayMillis
     * @invarient messagesToLog != null
     * @invarient logOutput != null
     * @invarient loggerLock != null
     * @invarient [The logger thread name is either DEFAULT_NAME or user-provided.]
     */

    public static final String DEFAULT_NAME = "Logger";

    private static final int DEFAULT_CYCLE_DELAY = 500;

    private MessageType logLevel = MessageType.INFO;
    private int cycleDelayMillis = DEFAULT_CYCLE_DELAY;
    private Queue<Message> messagesToLog = new ConcurrentLinkedQueue<>();

    private ILogOutput logOutput;

    private boolean isRunning;
    private Lock loggerLock = new ReentrantLock();

    /**
     * Constructs a Logger with a log level (the lowest priority message type to log)
     * of MessageType.INFO and the default logger name.
     *
     * @post [logOutput is a StandardLogOutput.]
     * @post [The thread name is the DEFAULT_NAME.]
     */
    public Logger() {
        logOutput = new StandardLogOutput();
        setName(DEFAULT_NAME);
    }

    /**
     * Constructs a Logger with a log level (the lowest priority message type to log)
     * of MessageType.INFO and the given logger output and name.
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
                logMessage(getName(), "Log gathering cycle interrupted!", MessageType.ERROR);//TODO: warning instead?
            }

            loggerLock.lock();
        }

        loggerLock.unlock();
    }


    public void logMessage(String source, String message, MessageType type) {
        if (!type.lessThan(logLevel)) {
            messagesToLog.add(new Message(source, message, type));
        }
    }

    /**
     * This method provides a simple way of logging a debug message with a given source.
     *
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     *
     * @param source is the source of the message.
     * @param message is the debug message to log.
     */
    public void logDebugMessage(String source, String message) {
        logMessage(source, message, MessageType.DEBUG);
    }

    /**
     * This method provides a simple way of logging an information message with a given thread
     * as its source.
     *
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     *
     * @param source is the source of the message.
     * @param message is the info message to log.
     */
    public void logInfoMessage(String source, String message) {
        logMessage(source, message, MessageType.INFO);
    }

    /**
     * This method provides a simple way of logging a warning message with a given thread
     * as its source.
     *
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     *
     * @param source is the source of the message.
     * @param message is the warning message to log.
     */
    public void logWarnMessage(String source, String message) {
        logMessage(source, message, MessageType.WARNING);
    }

    /**
     * This method provides a simple way of logging an error message with a given thread
     * as its source.
     *
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     *
     * @param source is the source of the message.
     * @param message is the error message to log.
     */
    public void logErrorMessage(String source, String message) {
        logMessage(source, message, MessageType.ERROR);
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

    public MessageType getLogLevel() {
        return logLevel;
    }

    public int getCycleDelay() {
        return cycleDelayMillis;
    }

    /**
     * @pre logLevel != null
     * @param logLevel is the message type with the minimum priority that the logger will log.
     */
    public void setLogLevel(MessageType logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * @pre 0 <= milliseconds
     * @param milliseconds is the number of milliseconds to collect logs before outputting them.
     */
    public void setCycleDelay(int milliseconds) {
        cycleDelayMillis = milliseconds;
    }
}
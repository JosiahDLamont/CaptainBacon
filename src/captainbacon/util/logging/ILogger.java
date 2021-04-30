package captainbacon.util.logging;

/**
 * @author Philip Cooper
 * Defines basic logger functionality.
 *
 * Defines:
 *      Message_Queue: [a queue of messages]
 *      Log_Level: [a MessageType]
 *      Is_Active: Boolean
 *
 * Initialization Ensures:
 *      Message_Queue is
 */
public interface ILogger {

    /**
     * Starts the logger.
     *
     * @post the logger is active.
     */
    void start();

    /**
     * This method provides the given message to the logger if its type has sufficient priority.
     *
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     * @pre type != null
     *
     * @post [Message_Queue contains a message with the given source, contents, and type]
     *       iff not type.lessThan(Log_Level)
     *
     * @param source  is the source of the message to attempt to log.
     * @param message is the contents of the message to attempt to log.
     * @param type    is the type of the message to attempt to log.
     */
    void logMessage(String source, String message, MessageType type);


}


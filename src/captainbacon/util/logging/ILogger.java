package captainbacon.util.logging;

public interface ILogger {

    void start();

    /**
     * This method provides the given message to the logger assuming its type has sufficient priority.
     *
     * @param source  is the source of the message to attempt to log.
     * @param message is the contents of the message to attempt to log.
     * @param type    is the type of the message to attempt to log.
     * @pre source != null and [is the source of the message]
     * @pre message != null and [contains the contents of the message]
     * @pre type != null
     * @post [messagesToLog contains a message with the given source, contents, and type]
     * iff not type.lessThan(logLevel)
     */

    void logMessage(String source, String message, MessageType type);


}


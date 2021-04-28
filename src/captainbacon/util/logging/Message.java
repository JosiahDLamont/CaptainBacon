package captainbacon.util.logging;

/**
 * @author Philip Cooper
 * This class defines a basic logger message with a type and a source.
 */
class Message {

    /**
     * @invarient type != null
     * @invarient source != null
     * @invarient message != null
     */

    private final String source;
    private final String message;
    private final MessageType type;


    /**
     * Constructs a message of the given type.
     *
     * @param source  is the source (usually a thread) of the message.
     * @param message is the message to log.
     * @param type    is the type of message.
     * @pre source != null
     * @pre message != null
     * @pre type != null
     * @post this.source = source
     * @post this.message = message
     * @post this.type = type
     */
    public Message(String source, String message, MessageType type) {
        this.source = source;
        this.message = message;
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }
}

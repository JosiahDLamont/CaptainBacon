package captainbacon.util.logging;

/**
 * @author Philip Cooper
 * This class defines a basic logger message with a priority and a source.
 */
public class Message {

    /**
     * @invarient MIN_PRIORITY <= priority <= MAX_PRIORITY
     * @invarient SOURCE != null
     * @invarient MESSAGE != null
     */

    public static final int MIN_PRIORITY = 0;
    public static final int LOW_PRIORITY = 2;
    public static final int BASE_PRIORITY = 5;
    public static final int HIGH_PRIORITY = 8;
    public static final int MAX_PRIORITY = 10;

    private final String source;
    private final String message;
    private int priority;

    /**
     * Constructs a message with the base priority.
     *
     * @pre source != null
     * @pre message != null
     *
     * @post SOURCE = source
     * @post MESSAGE = message
     * @post priority = BASE_PRIORITY
     *
     * @param source is the source (usually a thread) of the message.
     * @param message is the message to log.
     */
    public Message(String source, String message) {
        this.source = source;
        this.message = message;
        priority = BASE_PRIORITY;
    }

    /**
     * Constructs a message with the given priority.
     *
     * @pre source != null
     * @pre message != null
     * @pre MIN_PRIORITY <= priority <= MAX_PRIORITY
     *
     * @post SOURCE = source
     * @post MESSAGE = message
     * @post this.priority = priority
     *
     * @param source is the source (usually a thread) of the message.
     * @param message is the message to log.
     * @param priority is the priority of the message.
     */
    public Message(String source, String message, int priority) {
        this.source = source;
        this.message = message;
        this.priority = priority;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * @pre MIN_PRIORITY <= priority <= MAX_PRIORITY
     * @param priority is the priority of the message with the priority increasing as the value
     *                 moves from MIN_PRIORITY to MAX_PRIORITY.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}

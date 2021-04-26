package captainbacon.util.logging;

/**
 * @author Philip Cooper
 * This enum defines basic message types. These message types allow
 * messages to be processed by priority. They also provide an associated
 * name for each message type.
 */
public enum MessageType {
    DEBUG(0, "DEBUG"),
    INFO(1, "INFO"),
    WARNING(2, "WARN"),
    ERROR(3, "ERROR");

    private final int priority;
    private final String typeName;

    MessageType(int priority, String typeName) {
        this.priority = priority;
        this.typeName = typeName;
    }

    /**
     * Determines if the current message type is of equal or greater priority
     * to the given message type.
     *
     * @param messageType is the message type to which to compare this message type.
     * @return true if this message type has a priority equal to or greater than that
     *         of the given message type.
     */
    public boolean atOrAbove(MessageType messageType) {
        return priority >= messageType.priority;
    }

    public String getTypeName() {
        return typeName;
    }
}
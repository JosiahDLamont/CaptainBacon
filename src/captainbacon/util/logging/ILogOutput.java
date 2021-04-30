package captainbacon.util.logging;

/**
 * @author Philip Cooper
 * Defines the basic functionality of a log output.
 */
public interface ILogOutput {

    /**
     * Outputs the provided log message and source to an associated output medium.
     *
     * @pre source != null
     * @pre message != null
     *
     * @post [The log message and source are output.]
     *
     * @param source is the source of the message (normally a thread name).
     * @param message is the log message to output.
     */
    void outputLog(String source, String message);
}

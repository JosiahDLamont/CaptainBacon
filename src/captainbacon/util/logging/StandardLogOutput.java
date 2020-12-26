package captainbacon.util.logging;

public class StandardLogOutput implements ILogOutput {
    @Override
    public void outputInfoLog(String source, String message) {
        System.out.println("[" + source + "]: " + message);
    }
}

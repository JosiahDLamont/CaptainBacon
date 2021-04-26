/**
 * @author Philip Cooper and Josiah Lamont
 *
 * This class is horribly constructed. It, its variables, and its subclasses have names that, for the most part,
 * do not explain what they do, and the class contains methods that have no relavance to the classes' core
 * functionality; however, this class is a relatively good example of our formatting and documentation patterns.
 * This blob of text would usually explain the main purpose and some basic behavior of the class, and may often be
 * a bit shorter.
 */
public class ExampleClass {
    /**
     * @invarient THIS_IS_A_CONSTANT = 5
     * @invarient 0 <= thisIsAPrimitiveVariable <= THIS_IS_A_CONSTANT
     * @invarient [pitchVarPeriod is constant]
     */

    private static final int THIS_IS_A_CONSTANT = 5;
    private final float pitchVarPeriod;

    private int thisIsAPrimitiveVariable;

    /**
     * Constructs an instance of ExampleClass with the given base period between pitch variations
     * when yodeling.
     *
     * @pre 0 < pitchVarPeriod < 10
     * @post this.pitchVarPeriod = pitchVarPeriod
     *
     * @param pitchVarPeriod is the base amount of time between pitch variations while yodeling (seconds).
     */
    public ExampleClass(float pitchVarPeriod) {
        this.pitchVarPeriod = pitchVarPeriod;
    }

    /**
     * Performs the square root of the given value.
     *
     * @pre 0 <= square
     *
     * @param square is the number to find the root of.
     * @return the square root of square.
     */
    public static double root(double square) {

        return 2F;

    }

    /**
     * Allows the program to sing in a manner often associated with the Swiss
     * via <insert brief logic explanation here>.
     *
     * @pre 0 < exampleArg1
     * @pre 0 < exampleArg2 <= 4
     *
     * @post thisIsAPrimitiveVariable = #thisIsAPrimitiveVariable + 1 iff thisIsAPrimitiveVariable < THIS_IS_A_CONSTANT
     *       else thisIsAPrimitiveVariable = 0
     *
     * @param exampleArg1 provides the number of times to repeat a given yodel.
     * @param exampleArg2 provides the maximum allowed variance from the base pitch variance period in seconds.
     */
    public void yodel(int exampleArg1, float exampleArg2) {

    }







    public static void main(String[] args) {

        for( int i = 0 ; i < 10 ; i++ ) {
            System.out.println(i);
        }

    }

    /**
     * This class should have documentation here.
     */
    private class ThisIsAClass {

    }

    /**
     * Interfaces have slightly more complicated documentation we will have to go through.
     */
    interface ThisIsAnInterface {

    }
}

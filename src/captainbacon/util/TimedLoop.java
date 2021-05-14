package captainbacon.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class TimedLoop extends Thread {

    /**
     * @invariant tickRate > 0
     * @invariant realTickRate > 0
     */

    private boolean loopOver = false;
    protected final Lock loopLock = new ReentrantLock();

    private final int tickRate;
    private double realTickRate;
    private long startTickMillis;

    /**
     * Constructs a loop thread with the given tick rate.
     *
     * @pre tickRate > 0
     * @post this.tickRate = tickRate
     * @post realTickRate = tickRate
     *
     * @param tickRate is the number of times to run the loop action per second should performance allow it.
     */
    public TimedLoop(int tickRate) {
        this.tickRate = tickRate;
    }


    /**
     * Use this function to perform a set of tasks approximately tickRate times per second.
     */
    protected abstract void doLoopAction();

    /**
     * This method contains the timed loop and is automatically run when the start() method (from Thread)
     * is called.
     */
    @Override
    public void run() {
        loopLock.lock();

        // Try to execute the specified loop action tickRate times per second.
        while (!loopOver) {
            loopLock.unlock();

            startTick();
            doLoopAction();
            endTick();

            loopLock.lock();
        }

        loopLock.unlock();
    }


    /**
     * This method notifies the loop that it should end as soon as possible.
     */
    public void endLoop() {
        loopLock.lock();
        loopOver = true;
        loopLock.unlock();
    }


    /**
     * @return the tick rate set in the program in ticks-per-second. This tick rate is not guaranteed
     *         in practice.
     */
    public int getTickRate() {
        return tickRate;
    }

    /**
     * @return the effective tick rate at which the program completed the work of the most
     *         recent tick.
     */
    public double getRealTickRate() {
        return realTickRate;
    }

    /**
     * This method tells the loop thread when a tick starts. Used for tick timing.
     *
     * @pre [There is a call to endTick() between any previous calls to startTick()
     *       and this call to startTick()]
     *
     * @post startTickMillis = [the current system time]
     */
    protected void startTick() {
        startTickMillis = System.currentTimeMillis();
    }

    /**
     * This method tells the loop thread when a tick's work has finished. If the
     * tick ended with time to spare before the next tick, the thread sleeps through
     * the extra time. It also calculates the real tick rate.
     *
     * @pre [startTick() has been called and there are no other calls to endTick() between
     *       the call to startTick() and this call to endTick()]
     *
     * @post [The tick has lasted at least 1 / tickRate seconds.]
     * @post realTickRate = [the actual rate at which a tick's work is executed]
     */
    protected void endTick() {
        long elapsedTime = System.currentTimeMillis() - startTickMillis;

        // Calculate the real tick rate in seconds.
        realTickRate = 1000F / elapsedTime;

        // Determine if the elapsed time is less than the target milliseconds per tick.
        if (elapsedTime < 1000 / tickRate) {
            // There is extra time in the tick, try to sleep it off.
            try {
                sleep(1000 / tickRate - elapsedTime);
            } catch (InterruptedException e) {
                //logErrMessage("Tick interrupted!");
            }
        }
    }
}

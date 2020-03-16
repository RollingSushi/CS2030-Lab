/**
 * Creates an object to track current server availability and statistics such as.
 * (i) Total time customers spent waiting
 * (ii) Total customers served
 */

public class ServeState implements Comparable<ServeState> {
    /* avail enumeration is as such 
     * Note only 1 can be served at any point of time
     * 0: Serving: 1 Wait: 1 Free: 0
     * 1: Serving: 1 Wait: 0 Free: 1
     * 2: Serving: 0 Wait: 0 Free: 2
     */

    private final double currTime;
    public final int avail;
    private final double nextPossibleServeTime;
    public final double totalWaitTime;
    private final int totalCusServed;

    public ServeState(double currTime, int avail, double nextPossibleServeTime,
                      double totalWaitTime, int totalCusServed) {
        this.currTime = currTime;
        this.avail = avail;
        this.nextPossibleServeTime = nextPossibleServeTime;
        this.totalWaitTime = totalWaitTime;
        this.totalCusServed = totalCusServed;
    }

    /**
     * Updates currentTime, availability and nextPossibleServeTime.
     * @param currTime The current session time
     * @param avail Availability of server
     * @param nextPossibleServeTime The next time a customer can be served
     * @return A new ServeState object with the updated variables.     
     */

    public ServeState updateState(double currTime, int avail, double nextPossibleServeTime) {
        return new ServeState(currTime, avail, nextPossibleServeTime,
                              this.totalWaitTime, this.totalCusServed);
    }
    
    /**
     * Updates currentTime, availability, total wait time and total customers served.
     * @param currTime The current session time
     * @param avail Availability of server
     * @param totalWaitTime Total time customers spent waiting
     * @param totalCusServed Total number of customers served
     * @return A new ServeState object with the updated variables.
     */
    public ServeState updateState(double currTime, int avail,
                                  double totalWaitTime, int totalCusServed) {
        return new ServeState(currTime, avail, this.nextPossibleServeTime,
                              totalWaitTime, totalCusServed);
    }

    /**
     * Updates currentTime, availability and total customers served.
     * @param currTime The current session time
     * @param avail Availability of server
     * @param totalCusServed Total number of customers served
     * @return A new ServeState object with the updated variables.
     */

    public ServeState updateState(double currTime, int avail, int totalCusServed) {
        return new ServeState(currTime, avail, this.nextPossibleServeTime,
                              this.totalWaitTime, totalCusServed);
    }

    public int getTotalCusServed() {
        return this.totalCusServed;
    }

    public double getNextPossibleServeTime() {
        return this.nextPossibleServeTime;
    }

    public double getTotalWaitTime() {
        return this.totalWaitTime;
    }

    /**
     * Comparable method to prioritize the states with the earlier time.
     * @param servestate compares 2 ServeStates
     * @return int where -1 has a higher priority than 1
     */

    public int compareTo(ServeState servestate) {
        if (this.currTime < servestate.currTime) {
            return -1;
        } else if (this.currTime > servestate.currTime) {
            return 1;
        } else {
            return 0;
        }
    }

}
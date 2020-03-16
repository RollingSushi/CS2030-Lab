/**
 * Child class of Customer with an extra variable that tracks
 * the state of each customer. The variable arrTime contains the
 * value of completion time if the state is DONE or WAIT.
 * The toString method now includes the customer's state.
 */

public class CustomerState extends Customer implements Comparable<CustomerState> {
    public final State state;

    public CustomerState(int id, double arrTime, State state) {
        super(id, arrTime);
        this.state = state;
    }

    /**
     * Comparator method that prioritizes customers that arrive earlier.
     * If two customers have the same id, the smaller id takes priority
     * @param customers compares 2 CustomerState
     * @return int where -1 has a higher priority than 1
     */

    public int compareTo(CustomerState customers) {
        if (this.arrTime < customers.getArrTime()) {
            return -1;
        } else if (this.arrTime > customers.getArrTime()) {
            return 1;
        } else if (this.arrTime == customers.getArrTime() && this.id > customers.getId()) {
            return 1;
        } else {
            return 0;
        }
    } 

    @Override
    public String toString() {
        switch (this.state) {
            case ARRIVES:
                return String.format("%.3f", arrTime) + " " + id + " arrives";
            case SERVED:
                return String.format("%.3f", arrTime) + " " + id + " served";
            case LEAVES:
                return String.format("%.3f", arrTime) + " " + id + " leaves";
            case DONE:
                return String.format("%.3f", arrTime) + " " + id + " done";
            case WAITS:
                return String.format("%.3f", arrTime) + " " + id + " waits";
            default:
                return String.format("%.3f", arrTime) + " " + id;
        }
    }
}
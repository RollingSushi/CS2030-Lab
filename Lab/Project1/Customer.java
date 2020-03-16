/**
 * Creates a customer object. Getters for each variable are available
 * along with a toString method stating the arrival time and id
 */

public class Customer {
    protected final int id;
    protected final double arrTime;

    public Customer(int id, double arrTime) {
        this.id = id;
        this.arrTime = arrTime;
    }

    public int getId() {
        return this.id;
    }

    public double getArrTime() {
        return this.arrTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f", arrTime) + " " + id;
    }
}
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Receives a series of customers and their respective arrival times in double format
 * and displays the sequence of events that follows, classifying it as a session.
 * The session's statistics are also displayed after the events.
 */
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> allCustomers = new ArrayList<Customer>();

        //Creating and adding starting serve state
        PriorityQueue<CustomerState> allCustomersQ = new PriorityQueue<CustomerState>();
        PriorityQueue<ServeState> serveState = new PriorityQueue<ServeState>();
        ServeState startState = new ServeState(0.00, 2, 0.00, 0.00, 0);
        serveState.add(startState);
        int id = 1;

        while (sc.hasNextDouble()) {
            double arrTime = sc.nextDouble();
            allCustomers.add(new Customer(id, arrTime));
            allCustomersQ.add(new CustomerState(id, arrTime, State.ARRIVES));
            id++;
        }
        sc.close();

        PriorityQueue<CustomerState> sessionResult = new PriorityQueue<CustomerState>();

        SessionHandler session1 = new SessionHandler(allCustomersQ, serveState, sessionResult);

        for (CustomerState c: session1.runSession()) {
            System.out.println(c);
        }
        
        System.out.println(session1.getStatistics(id));
        
    }
}


import java.util.PriorityQueue;

/**
 * An object to handle the unfolding of events and statistics
 * of arriving customers. Provides provides methods to
 * (i) Run a session
 * (ii) Get statistics of a session
 */

public class SessionHandler {
    public PriorityQueue<CustomerState> allCustomersQ;
    public PriorityQueue<ServeState> serveState;
    public PriorityQueue<CustomerState> sessionResult;

    public SessionHandler(PriorityQueue<CustomerState> allCustomersQ,
                          PriorityQueue<ServeState> serveState,
                          PriorityQueue<CustomerState> sessionResult) {

        this.allCustomersQ = allCustomersQ;
        this.serveState = serveState;
        this.sessionResult = sessionResult;
    }

    /**
     * Contains the logic for creating the sequence of events
     * Only 1 customer can be served and 1 waiting.
     * @return A complete, ordered sequence of events in a priority queue
     */
    
    public PriorityQueue<CustomerState> runSession() {

        while (!allCustomersQ.isEmpty()) {
            CustomerState firstQ = allCustomersQ.peek();
            ServeState firstStateQ = serveState.peek();

            if (firstQ.state == State.ARRIVES && firstStateQ.avail == 2 /*Totally free*/) {
                sessionResult.add(allCustomersQ.peek());
                
                allCustomersQ.add(new CustomerState(firstQ.getId(),
                                                    firstQ.getArrTime(), State.SERVED));

                ServeState nextState = serveState.poll().updateState(firstQ.getArrTime(),
                                                                     1, firstQ.getArrTime() + 1.00);

                serveState.add(nextState);
                allCustomersQ.poll();

            } else if (firstQ.state == State.ARRIVES && firstStateQ.avail == 1 /*Can wait 1*/) {
                sessionResult.add(allCustomersQ.peek());
                sessionResult.add(new CustomerState(
                                  allCustomersQ.peek().getId(),
                                  firstQ.getArrTime(), State.WAITS)
                                  );

                allCustomersQ.add(new CustomerState(
                                  allCustomersQ.peek().getId(),
                                  firstStateQ.getNextPossibleServeTime(), State.WAITS)
                                  );

                ServeState nextState = firstStateQ.updateState(
                                       firstQ.getArrTime(), 0,
                                       firstStateQ.getTotalWaitTime() +
                                       firstStateQ.getNextPossibleServeTime() -
                                       allCustomersQ.peek().getArrTime(), 
                                       serveState.poll().getTotalCusServed()
                                       );

                allCustomersQ.poll();
                serveState.add(nextState);

            } else if (firstQ.state == State.ARRIVES && firstStateQ.avail == 0 /*Can wait 0*/) {
                sessionResult.add(allCustomersQ.peek());

                allCustomersQ.add(new CustomerState(allCustomersQ.poll().getId(),
                                                    firstQ.getArrTime(), State.LEAVES));

                
            } else if (firstQ.state == State.SERVED) {
                sessionResult.add(allCustomersQ.peek());
                allCustomersQ.add(new CustomerState(allCustomersQ.poll().getId(),
                                                    firstQ.getArrTime() + 1.00, State.DONE));
                
            } else if (firstQ.state == State.LEAVES) {
                sessionResult.add(allCustomersQ.peek());
                allCustomersQ.poll();

            } else if (firstQ.state == State.DONE) {
                sessionResult.add(allCustomersQ.peek());

                if (firstStateQ.avail == 0) {
                    ServeState nextState = firstStateQ.updateState(
                                           firstQ.getArrTime(), 1,
                                           serveState.poll().getTotalCusServed() + 1
                                           );
                    
                    serveState.add(nextState);
                    allCustomersQ.poll();
                } else {
                    ServeState nextState = firstStateQ.updateState(
                                           firstQ.getArrTime(), 2,
                                           serveState.poll().getTotalCusServed() + 1
                                           );

                    serveState.add(nextState);
                    allCustomersQ.poll();
                }
                    
            } else {
                allCustomersQ.add(new CustomerState(firstQ.getId(),
                                                    firstQ.getArrTime(), State.SERVED));

                ServeState nextState = serveState.poll().updateState(
                                                         firstQ.getArrTime(), 1,
                                                         firstQ.getArrTime() + 1.00
                                                         );

                serveState.add(nextState);
                allCustomersQ.poll();

            }
        }  
        return sessionResult;
    }
    
    /**
     * Calculates the statistics of a session.
     * @param totalCus Total number of customers
     * @return A string with the information of average wait time,
     *     total customers served and total customers not served
     */

    public String getStatistics(int totalCus) {
        this.runSession();
        double averageWaitTime = (this.serveState.peek().getTotalWaitTime() / 
                                  this.serveState.peek().getTotalCusServed());

        String customersNotServed = String.valueOf(
                                    totalCus - 1 -
                                    this.serveState.peek().getTotalCusServed()
                                    );

        String customersServed = String.valueOf(this.serveState.peek().getTotalCusServed());

        return "[" +  String.format("%.3f", averageWaitTime) +
                                    " " + customersServed +
                                    " " + customersNotServed + "]";
    }

    
}
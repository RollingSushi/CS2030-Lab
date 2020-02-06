import java.lang.String;

public class Loader {
    private final int id;
    private final int servState;
    private final int serviceCompletionTime;
    public final String serving;
    private final int extraDownTime;

    public Loader(int id) {
        this.id = id;
        this.servState = 0;
        this.serviceCompletionTime = 0;
        this.serving = "blank";
        this.extraDownTime = 0;
    }

    public Loader(int id, int servState, int serviceCompletionTime,
                  String serving, int extraDownTime) {
        this.id = id;
        this.servState = servState;
        this.serviceCompletionTime = serviceCompletionTime;
        this.serving = serving;
        this.extraDownTime = extraDownTime;
    }
    
    public boolean canServe(Cruise cruise) {
        if (servState == 0) {
            return true;
        } else if (this.serviceCompletionTime <= cruise.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    public Loader serve(Cruise cruise) {  
        if (cruise == null) {
            return new Loader(this.id);
        } else if (this.canServe(cruise)) {
            Loader newLoad = new Loader(this.id, 1, cruise.getServiceCompletionTime() +
                                        this.extraDownTime, cruise.toString(), 0);  
            return newLoad;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (this.servState == 1) {
            return "Loader " + this.id + " serving " + this.serving;
        } else {
            return "Loader " + this.id;
        }
        
    }


}
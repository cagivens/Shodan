package rosterizer;

import java.util.ArrayList;
import java.util.List;

public class Associate {

    public enum TrainedRole {
        AMBASSADOR,
        END_OF_LINE,
        PROBLEM_SOLVE,
        WATERSPIDER,
        REFURB,
        WHD,
        RECOVERY,
        OUTBOUND,
        SHOE_PROCESSING,
        ICQA,
        TDR,
        PIT
    }

    private final String LOGIN;
    private final ArrayList<TrainedRole> ROLES = new ArrayList<>();
    private double averageRate = 0.0;
    private double rate = 0.0;
    private double inferredTime = 0.0;

    public Associate(String login) { this.LOGIN = login; }

    public String getLogin() { return LOGIN; }
    public double getRate() { return rate; }
    public double getAverageRate() { return averageRate; }
    public double getInferredTime() { return inferredTime; }
    public List<TrainedRole> getRoles() { return ROLES; }

    public void setRate(double rate) { this.rate = rate; }
    public void setAverageRate(double averageRate) { this.averageRate = averageRate; }
    public void setInferredTime(double inferredTime) { this.inferredTime = inferredTime; }

    public void addTrainedRole(TrainedRole newRole) {
        if(ROLES.contains(newRole))
            return;
        ROLES.add(newRole);
    }
}

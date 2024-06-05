package rosterizer;

import java.util.ArrayList;
import java.util.List;

public class Associate {

    public static int ROLE_AMBASSADOR = 0b000000000001;
    public static int ROLE_EOL = 0b000000000010;
    public static int ROLE_PS = 0b000000000100;
    public static int ROLE_WATERSPIDER = 0b000000001000;
    public static int ROLE_REFURB = 0b000000010000;
    public static int ROLE_WHD = 0b000000100000;
    public static int ROLE_RECOVERY = 0b000001000000;
    public static int ROLE_OUTBOUND = 0b000010000000;
    public static int ROLE_SHOES = 0b000100000000;
    public static int ROLE_ICQA = 0b001000000000;
    public static int ROLE_TDR = 0b010000000000;
    public static int ROLE_PIT = 0b100000000000;


    private final String LOGIN;
    private int roles = 0;
    private double averageRate = 0.0;
    private double rate = 0.0;
    private double inferredTime = 0.0;

    public Associate(String login) { this.LOGIN = login; }

    public String getLogin() { return LOGIN; }
    public double getRate() { return rate; }
    public double getAverageRate() { return averageRate; }
    public double getInferredTime() { return inferredTime; }
    public boolean hasRole(int role) { return false; }

    public void setRate(double rate) { this.rate = rate; }
    public void setAverageRate(double averageRate) { this.averageRate = averageRate; }
    public void setInferredTime(double inferredTime) { this.inferredTime = inferredTime; }

    public void addTrainedRole(int newRole) {
        roles = roles | newRole;
    }
}

package rosterizer;

public class Associate {

    public static int ROLE_AMBASSADOR = 0b0000_00000001;
    public static int ROLE_EOL = 0b0000_00000010;
    public static int ROLE_PS = 0b0000_00000100;
    public static int ROLE_WATERSPIDER = 0b0000_00001000;
    public static int ROLE_REFURB = 0b0000_00010000;
    public static int ROLE_WHD = 0b0000_00100000;
    public static int ROLE_RECOVERY = 0b0000_01000000;
    public static int ROLE_OUTBOUND = 0b0000_10000000;
    public static int ROLE_SHOES = 0b0001_00000000;
    public static int ROLE_ICQA = 0b0010_00000000;
    public static int ROLE_TDR = 0b0100_00000000;
    public static int ROLE_PIT = 0b1000_00000000;

    private final String LOGIN;
    private int roles = 0;
    private double rate = 0.0;
    private double inferredTime = 0.0;

    public Associate(String login) { this.LOGIN = login; }

    public String getLogin() { return LOGIN; }
    public double getRate() { return rate; }
    public double getInferredTime() { return inferredTime; }

    public void setRate(double rate) { this.rate = rate; }
    public void setInferredTime(double inferredTime) { this.inferredTime = inferredTime; }
    public void addTrainedRole(int newRole) { roles = roles | newRole; }

    public boolean hasRole(int role) {
        return false;
    }

    @Override
    public String toString() {
        return LOGIN + " {" + roles + "}";
    }
}
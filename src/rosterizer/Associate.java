package rosterizer;

public class Associate {
    final static int ROLE_AMBASSADOR = 1;
    final static int ROLE_EOL = 2;
    final static int ROLE_PS = 4;
    final static int ROLE_WATERSPIDER = 8;
    final static int ROLE_REFURB = 16;
    final static int ROLE_WHD = 32;
    final static int ROLE_RECOVERY = 64;
    final static int ROLE_OUTBOUND = 128;
    final static int ROLE_PROCESS = 256;
    final static int ROLE_ICQA = 512;
    final static int ROLE_TDR = 1024;
    final static int ROLE_PIT = 2048;

    final static String SORT_SUN = "SUN";
    final static String SORT_DAY = "DAY";
    final static String SORT_TWI = "TWI";
    final static String SORT_NIT = "NIT";

    private int roles = 0;
    private double averageRate = 0.0;
    private double averageInferredTime = 0.0;
    private String sort;
    private final String username;

    public Associate(String username) {
        this.username = username;
    }

    public void addTrainedRole(int newRole) {
        roles = roles | newRole;
    }
    public boolean hasRole(int role) {
        return (roles & role) == role;
    }

    public String getUsername() { return username; }
    public String getSort() { return sort; }

    public void setSort(String sort) { this.sort = sort; }

    @Override
    public String toString() {
        return String.format("%s {%d}", username, roles);
    }
}

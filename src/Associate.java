public class Associate {
    public static final int ROLE_AMBASSADOR = 1;
    public static final int ROLE_EOL = 2;
    public static final int ROLE_PS = 4;
    public static final int ROLE_WATERSPIDER = 8;
    public static final int ROLE_REFURB = 16;
    public static final int ROLE_WHD = 32;
    public static final int ROLE_RECOVERY = 64;
    public static final int ROLE_OUTBOUND = 128;
    public static final int ROLE_PROCESS = 256;
    public static final int ROLE_ICQA = 512;
    public static final int ROLE_TDR = 1024;
    public static final int ROLE_PIT = 2048;

    private int roles = 0;
    private final String username;
    private final String name;

    public Associate(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public void addTrainedRole(int newRole) {
        roles = roles | newRole;
    }

    public boolean hasRole(int role) {
        return (roles & role) == role;
    }

    public String getUsername() { return username; }

    @Override
    public String toString() {
        return String.format("%s,%s,%d", name, username, roles);
    }
}

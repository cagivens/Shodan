public class Associate {

    public enum Role {
        AMBASSADOR(1),
        EOL(2),
        PS(4),
        WATERSPIDER(8),
        REFURB(16),
        WHD(32),
        RECOVERY(64),
        OUTBOUND(128),
        PROCESS(256),
        ICQA(512),
        TDR(1024),
        PIT(2048),
        SHOES(4096);

        final int value;

        Role(int value) {
            this.value = value;
        }
    }

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

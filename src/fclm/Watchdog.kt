package fclm;

public abstract class Watchdog {
    private String handle;

    public Watchdog(String handle) { this.handle = handle; }

    public String getHandle() { return handle; }
}

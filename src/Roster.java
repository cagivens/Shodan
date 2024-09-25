import java.util.*;

public class Roster {
    private HashMap<Integer, LinkedList<String>> assignments;
    private HashMap<String, Associate> associates;
    private AssociateDB database;

    private int maxPS;
    private int maxWS;
    private int maxEOL;

    public Roster(int maxPS, int maxWS, int maxEOL, AssociateDB database) {
        assignments = new HashMap<>();
        associates = new HashMap<>();

        this.maxPS = maxPS;
        this.maxWS = maxWS;
        this.maxEOL = maxEOL;
        this.database = database;
    }

    public void setAssignments() {
        LinkedList<Associate> unassignedAssociates = new LinkedList<>(associates.values());
        List<Associate.Role> possibleRoles = Arrays.asList(Associate.Role.values());

        Collections.shuffle(unassignedAssociates);
        Collections.shuffle(possibleRoles);
        possibleRoles.remove(Associate.Role.PROCESS);


    }

    public void importSSPOT() {

    }

    @Override
    public String toString() {
        String result = "";
        return result;
    }
}
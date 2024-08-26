package rosterizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Roster {
    private HashMap<String, Associate> scheduledAssociates;
    private HashMap<Integer, LinkedList<Associate>> assignedRoles;
    private LinkedList<String> processAssistants;

    public Roster(List<String> processAssistants) {
        this.processAssistants = new LinkedList<>(processAssistants);
        this.scheduledAssociates = new HashMap<>();
        this.assignedRoles = new HashMap<>();

        this.resetRoles();
    }

    public void importSSPOT(String filepath) {
        if(filepath.isEmpty())
            return;

        LinkedList<String> rows = new LinkedList<String>();

        try {
            File file = new File(filepath);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine())
                rows.add(sc.nextLine());
            sc.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        rows.removeFirst();
        for(String row : rows) {
            Associate assoc = new Associate(row.split(",")[1].replace("\"", ""), "");

            if(processAssistants.contains(assoc.getUsername()))
                continue;
            scheduledAssociates.put(assoc.getUsername(), assoc);
        }
    }

    public void importTrainingQUIP(String filepath) {
        if(filepath.isEmpty())
            return;

        LinkedList<String> rows = new LinkedList<>();

        try {
            File file = new File(filepath);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine())
                rows.add(sc.nextLine());
            sc.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        rows.removeFirst();
        for(String row : rows) {
            String[] rowAsArray = row.substring(row.lastIndexOf('"') + 1, row.length() - 1).split(",");
            String username = rowAsArray[1];
            String process = rowAsArray[6].toLowerCase();

            if(scheduledAssociates.containsKey(username))
                switch(process) {
                    case "ambassador":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_AMBASSADOR);
                        break;
                    case "audit":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_RECOVERY);
                        break;
                    case "end of line":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_EOL);
                        break;
                    case "outbound":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_OUTBOUND);
                        break;
                    case "pit":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_PIT);
                        break;
                    case "problem solve":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_PS);
                        break;
                    case "refurb":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_REFURB);
                        break;
                    case "shoe icqa":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_ICQA);
                        break;
                    case "tdr":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_TDR);
                        break;
                    case "water spider":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_WATERSPIDER);
                        break;
                    case "whd":
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_WHD);
                        break;
                    default:
                        scheduledAssociates.get(username).addTrainedRole(Associate.ROLE_PROCESS);
                }
        }
    }

    public void randomizeRoles() {
        this.resetRoles();

        Set<String> keys = scheduledAssociates.keySet();
        String[] associates = keys.toArray(new String[0]);


    }

    public void exportRoster(String filepath) {
        File file = new File(filepath);
        byte[] bytes = this.toString().getBytes();

    }

    private void resetRoles() {
        int key = 1;
        while(key <= Associate.ROLE_PIT) {
            assignedRoles.put(key, new LinkedList<Associate>());
            key = key << 1;
        }
    }

    public List<Associate> getAssociateLoginsByRole(int role) {
        return new LinkedList<>(assignedRoles.get(role));
    }

    @Override
    public String toString() {
        String result = "";
        return result;
    }
}
package rosterizer;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Roster {
    private HashMap<Integer, LinkedList<String>> assignments;
    private HashMap<String, Associate> associates;
    private AssociateDB database;
    

    public Roster(AssociateDB database) {
        assignments = new HashMap<>();
        associates = new HashMap<>();

        this.database = database;
    }

    public void setAssignments() {
        LinkedList<Associate> unassignedAssociates = new LinkedList<>(associates.values());
        List<Associate.Role> possibleRoles = Arrays.asList(Associate.Role.values());

        Collections.shuffle(unassignedAssociates);
        Collections.shuffle(possibleRoles);

        for(Associate.Role role : possibleRoles)
            assignments.put(role.value, new LinkedList<>());

        for (int x = 0; x < possibleRoles.size(); x++) {
            Associate.Role currentRole = possibleRoles.get(x);

            for (int y = 0; y < unassignedAssociates.size(); y++) {
                Associate associate = unassignedAssociates.get(y);

                if (associate.hasRole(currentRole.value)) {
                    System.out.printf("%s has role %d\n", associate.getUsername(), currentRole.value);
                }

            }
        }
    }

    public void importSSPOT() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        LinkedList<String> rows = new LinkedList<>();
        String filepath = "";

        chooser.setMultiSelectionEnabled(true);
        int option = chooser.showOpenDialog(null);

        if(option == JFileChooser.APPROVE_OPTION)
            filepath = chooser.getSelectedFile().getAbsolutePath();

        try {
            File[] files = chooser.getSelectedFiles();

            for (File file : files) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine())
                    rows.add(sc.nextLine());
                sc.close();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        rows.removeFirst();

        for(int i = 0; i < rows.size(); i++) {
            String[] rowAsArray = rows.get(i).replace("\"", "").split(",");
            Associate assoc = database.getAssociate(rowAsArray[1]);
            if(assoc != null)
                associates.put(assoc.getUsername(), assoc);
            else
                associates.put(rowAsArray[1], new Associate(rowAsArray[1]));
        }
    }

    @Override
    public String toString() {
        String result = "";
        return result;
    }
}
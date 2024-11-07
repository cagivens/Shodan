package rosterizer;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Roster {
    private HashMap<Integer, LinkedList<String>> assignments;
    private HashMap<String, Associate> associates;
    private AssociateDB database;

    private int problemSolverMax = 5;
    private int waterspiderMax = 3;
    private int eolMax = 3;
    private int refurbMax = 2;
    private int whdMax = 1;
    private int icqaMax = 1;
    private int recoveryMax = 1;
    private int shoeProcessingMax = 8;
    

    public Roster(AssociateDB database) {
        assignments = new HashMap<>();
        associates = new HashMap<>();

        this.database = database;
    }

    public void setAssignments() {
        LinkedList<Associate> unassignedAssociates = new LinkedList<>(associates.values());

        Collections.shuffle(unassignedAssociates);

        // Selecting problem solvers
        assignments.put(Associate.Role.PS.value, new LinkedList<>());
        System.out.println("---PROBLEM SOLVERS---");
        for(int i = 0; assignments.get(Associate.Role.PS.value).size() < problemSolverMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.PS.value)) {
                assignments.get(Associate.Role.PS.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting waterspiders
        assignments.put(Associate.Role.WATERSPIDER.value, new LinkedList<>());
        System.out.println("---WATERSPIDERS---");
        for(int i = 0; assignments.get(Associate.Role.WATERSPIDER.value).size() < waterspiderMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.WATERSPIDER.value)) {
                assignments.get(Associate.Role.WATERSPIDER.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting eol
        assignments.put(Associate.Role.EOL.value, new LinkedList<>());
        System.out.println("---EOL---");
        for(int i = 0; assignments.get(Associate.Role.EOL.value).size() < eolMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.EOL.value)) {
                assignments.get(Associate.Role.EOL.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting whd
        assignments.put(Associate.Role.WHD.value, new LinkedList<>());
        System.out.println("---WHD---");
        for(int i = 0; assignments.get(Associate.Role.WHD.value).size() < whdMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.WHD.value)) {
                assignments.get(Associate.Role.WHD.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting refurb
        assignments.put(Associate.Role.REFURB.value, new LinkedList<>());
        System.out.println("---REFURB---");
        for(int i = 0; assignments.get(Associate.Role.REFURB.value).size() < refurbMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.REFURB.value)) {
                assignments.get(Associate.Role.REFURB.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting icqa
        assignments.put(Associate.Role.ICQA.value, new LinkedList<>());
        System.out.println("---ICQA---");
        for(int i = 0; assignments.get(Associate.Role.ICQA.value).size() < icqaMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.ICQA.value)) {
                assignments.get(Associate.Role.ICQA.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting recovery
        assignments.put(Associate.Role.RECOVERY.value, new LinkedList<>());
        System.out.println("---RECOVERY---");
        for(int i = 0; assignments.get(Associate.Role.RECOVERY.value).size() < recoveryMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.RECOVERY.value)) {
                assignments.get(Associate.Role.RECOVERY.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting shoe processing
        assignments.put(Associate.Role.SHOES.value, new LinkedList<>());
        System.out.println("---SHOE PROCESSING---");
        for(int i = 0; assignments.get(Associate.Role.SHOES.value).size() < shoeProcessingMax; i++)
            if(unassignedAssociates.get(i).hasRole(Associate.Role.SHOES.value)) {
                assignments.get(Associate.Role.SHOES.value).add(unassignedAssociates.get(i).getUsername());
                System.out.println(unassignedAssociates.get(i).getUsername());
                unassignedAssociates.remove(i);
            }
        // Selecting softlines processors
        assignments.put(Associate.Role.PROCESS.value, new LinkedList<>());
        System.out.println("---SOFTLINES PROCESSING---");
        for (Associate unassignedAssociate : unassignedAssociates) {
            assignments.get(Associate.Role.PROCESS.value).add(unassignedAssociate.getUsername());
            System.out.println(unassignedAssociate.getUsername());
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
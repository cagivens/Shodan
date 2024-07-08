package rosterizer;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Roster {
    private HashMap<String, Associate> scheduledAssociates;
    private HashMap<Integer, LinkedList<Associate>> assignedRoles;
    private LinkedList<String> processAssistants;

    public void importSSPOT(String filepath) {
        if(filepath.isEmpty())
            return;

        LinkedList<String> rows = new LinkedList();

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
            Associate assoc = new Associate(row.split(",")[1].replace("\"", ""));

            if(processAssistants.contains(assoc.getUsername()))
                continue;
            scheduledAssociates.put(assoc.getUsername(), assoc);
        }
    }

    public void importTrainingQUIP(String filepath) {
        if(filepath.isEmpty())
            return;

        LinkedList<String> rows = new LinkedList();

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
        }
    }

    public void randomizeRoles() {

    }
}

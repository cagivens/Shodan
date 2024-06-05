package rosterizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Roster {
    HashMap<String, String[]> sheet = new HashMap<>();
    LinkedList<Associate> scheduledAssociates;

    private Roster() {
        scheduledAssociates = new LinkedList<>();
    }

    public List<Associate> getScheduledAssociates() { return scheduledAssociates; }

    public static Roster importSSPOT(String filepath) {
        Roster result = new Roster();
        LinkedList<String> rows = new LinkedList<>();

        try {
            File file = new File(filepath);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine())
                rows.add(fileScanner.nextLine());
            fileScanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("FileNotFoundException thrown while importing SSPOT roster");
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        int count = 0;
        for(String s : rows) {
            System.out.printf("%d : %s\n", count++, s);
            result.getScheduledAssociates().add(new Associate(s.split(",")[1]));
        }

        return result;
    }
}

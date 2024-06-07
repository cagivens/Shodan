package rosterizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Roster {
    HashMap<String, Associate> scheduledAssociates;

    public Roster() {
        scheduledAssociates = new HashMap<>();
    }

    public HashMap<String, Associate> getScheduledAssociates() { return scheduledAssociates; }

    @Override
    public String toString() {
        return "";
    }

    public void importSSPOT(String filepath) {
        LinkedList<String> rows = new LinkedList<>();

        if(filepath.isEmpty())
            return;

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
        rows.removeFirst();
        for(String r : rows) {
            Associate a = new Associate(r.split(",")[1].replace("\"", ""));
            scheduledAssociates.put(a.getLogin(), a);

            System.out.printf("%d: %s\n", count++, a.getLogin());
        }
    }

    public static void importTrainingQuip(String filepath, HashMap<String, Associate> associates) {
        LinkedList<String> rows = new LinkedList<>();

        try {
            File file = new File(filepath);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine())
                rows.add(fileScanner.nextLine());
            fileScanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("FileNotFoundException thrown while importing Training QUIP roster");
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        rows.removeFirst();
        for(String s : rows) {
            String[] row = s.substring(s.lastIndexOf('"') + 1, s.length() - 1).split(",");
            String login = row[1];
            String process = row[6].toLowerCase();

            if(associates.containsKey(login))
                if(process.equals("ambassador"))
                    associates.get(login).addTrainedRole(Associate.ROLE_AMBASSADOR);
                else if(process.equals("audit"))
                    associates.get(login).addTrainedRole(Associate.ROLE_RECOVERY);
                else if(process.equals("end of line"))
                    associates.get(login).addTrainedRole(Associate.ROLE_EOL);
                else if(process.equals("outbound"))
                    associates.get(login).addTrainedRole(Associate.ROLE_OUTBOUND);
                else if(process.equals("pit"))
                    associates.get(login).addTrainedRole(Associate.ROLE_PIT);
                else if(process.equals("problem solve"))
                    associates.get(login).addTrainedRole(Associate.ROLE_PS);
                else if(process.equals("refurb"))
                    associates.get(login).addTrainedRole(Associate.ROLE_REFURB);
                else if(process.equals("shoe icqa"))
                    associates.get(login).addTrainedRole(Associate.ROLE_ICQA);
                else if(process.equals("shoe processing"))
                    associates.get(login).addTrainedRole(Associate.ROLE_SHOES);
                else if(process.equals("tdr"))
                    associates.get(login).addTrainedRole(Associate.ROLE_TDR);
                else if(process.equals("water spider"))
                    associates.get(login).addTrainedRole(Associate.ROLE_WATERSPIDER);
                else if(process.equals("whd"))
                    associates.get(login).addTrainedRole(Associate.ROLE_WHD);
        }
    }
}

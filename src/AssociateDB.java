import rosterizer.Associate;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class AssociateDB {

    private HashMap<String, Associate> associates = associates = new HashMap<>();

    public AssociateDB() throws IOException {

        LinkedList<String> rows = new LinkedList<>();
        File dbFile = new File("associate_database.csv");

        try {
            Scanner scanner = new Scanner(dbFile);

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine());

            scanner.close();
        } catch (FileNotFoundException e) {
            if(dbFile.createNewFile()) {
                System.out.println("Created new Associate database file.");
            }
        }

        for(int i = 0; i < rows.size(); i++) {
            String[] cells = rows.get(i).split(",");
            Associate assoc = new Associate(cells[0], "");
            assoc.addTrainedRole(Integer.parseInt(cells[1]));
        }

        System.out.println(rows);
    }

    public void readTrainingQUIP() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        LinkedList<String> rows = new LinkedList<>();
        String filepath = "";
        int option = chooser.showSaveDialog(null);

        if(option == JFileChooser.APPROVE_OPTION)
            filepath = chooser.getSelectedFile().getAbsolutePath();

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
            //String name = rowAsArray[]
            String username = rowAsArray[1];
            String process = rowAsArray[6].toLowerCase();

            if(associates.containsKey(username))
                switch(process) {
                    case "ambassador":
                        associates.get(username).addTrainedRole(Associate.ROLE_AMBASSADOR);
                        break;
                    case "audit":
                        associates.get(username).addTrainedRole(Associate.ROLE_RECOVERY);
                        break;
                    case "end of line":
                        associates.get(username).addTrainedRole(Associate.ROLE_EOL);
                        break;
                    case "outbound":
                        associates.get(username).addTrainedRole(Associate.ROLE_OUTBOUND);
                        break;
                    case "pit":
                        associates.get(username).addTrainedRole(Associate.ROLE_PIT);
                        break;
                    case "problem solve":
                        associates.get(username).addTrainedRole(Associate.ROLE_PS);
                        break;
                    case "refurb":
                        associates.get(username).addTrainedRole(Associate.ROLE_REFURB);
                        break;
                    case "shoe icqa":
                        associates.get(username).addTrainedRole(Associate.ROLE_ICQA);
                        break;
                    case "tdr":
                        associates.get(username).addTrainedRole(Associate.ROLE_TDR);
                        break;
                    case "water spider":
                        associates.get(username).addTrainedRole(Associate.ROLE_WATERSPIDER);
                        break;
                    case "whd":
                        associates.get(username).addTrainedRole(Associate.ROLE_WHD);
                        break;
                    default:
                        associates.get(username).addTrainedRole(Associate.ROLE_PROCESS);
                }
        }
    }

    public Associate getAssociate(String username) { return associates.get(username); }
}

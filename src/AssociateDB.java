import rosterizer.Associate;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AssociateDB {

    private HashMap<String, Associate> associates = associates = new HashMap<>();
    private final String DB_FILEPATH = "associate_database.csv";

    public AssociateDB() throws IOException {

        LinkedList<String> rows = new LinkedList<>();
        File dbFile = new File(DB_FILEPATH);

        try {
            Scanner scanner = new Scanner(dbFile);

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine());

            scanner.close();
        } catch (FileNotFoundException e) {
            if(dbFile.createNewFile()) {
                System.out.println("Created new rosterizer.Associate database file.");
            }
        }

        for(int i = 0; i < rows.size(); i++) {
            String[] cells = rows.get(i).split(",");
            Associate assoc = new Associate(cells[1], cells[0]);
            assoc.addTrainedRole(Integer.parseInt(cells[2]));
            associates.put(assoc.getUsername(), assoc);
        }

        System.out.println(rows);
    }

    public void readTrainingQUIP() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        LinkedList<String> rows = new LinkedList<>();
        String filepath = "";
        int option = chooser.showOpenDialog(null);

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
        for(int i = 0; i < rows.size(); i++) {
            String[] rowAsArray = rows.get(i).split(",");
            String name = (rowAsArray[0].replace("\"", "") + "_"
                    + rowAsArray[1].replace("\"", "")).replace(" ", "");
            String username = rowAsArray[2];
            String process = rowAsArray[7].toLowerCase();

            if(username.isEmpty())
                continue;
            if(!associates.containsKey(username))
                associates.put(username, new Associate(username, name));

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
        saveAssociateData(DB_FILEPATH);
    }

    public Associate getAssociate(String username) { return associates.get(username); }

    private void saveAssociateData(String filepath) {
        File file = new File(filepath);
        ArrayList<Associate> assocList = new ArrayList<>(associates.values());
        try (PrintWriter writer = new PrintWriter(file)) {
            for(int i = 0; i < assocList.size(); i++)
                writer.println(assocList.get(i));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
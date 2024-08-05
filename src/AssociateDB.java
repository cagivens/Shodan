import rosterizer.Associate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class AssociateDB {

    private HashMap<String, Associate> associates = associates = new HashMap<>();

    public AssociateDB() {

        String filename = "associate_database.csv";
        LinkedList<String> rows = new LinkedList<>();

        try {
            File dbFile = new File(filename);
            Scanner scanner = new Scanner(dbFile);

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine());

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException thrown in AssociateDB constructor.");
            System.exit(4);
        }

        // readin logic
    }

    public void readTrainingQUIP() {
        // Logic for reading training quip
    }

    public Associate getAssociate(String username) { return associates.get(username); }
}

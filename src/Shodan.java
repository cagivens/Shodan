import rosterizer.AssociateDB;
import rosterizer.Roster;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileOutputStream;
import java.io.IOException;

public class Shodan {

    public static void main(String[] args) {
        AssociateDB db;

        try {
            db = new AssociateDB();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //db.readTrainingQUIP();

        Roster roster = new Roster(db);

        roster.importSSPOT();
        roster.setAssignments();
    }

    public static void writeBytesToFile(String filepath, byte[] bytes) {
        try {
            FileOutputStream outStream = new FileOutputStream(filepath);
            outStream.write(bytes);
            outStream.close();
        } catch(IOException e) {
            System.out.println("IOException thrown in Shodan.writeBytesToFile");
        }
    }

    public static String showSaveDialog() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        int option = chooser.showSaveDialog(null);

        if(option == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return "";
    }

    public static String showOpenDialog() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        int option = chooser.showOpenDialog(null);

        if(option == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return "";

    }
}
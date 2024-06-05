import rosterizer.Associate;
import rosterizer.Roster;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Shodan extends JFrame {

    public Shodan(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static String getOpenFilepath() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        int r = chooser.showOpenDialog(null);

        if(r == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return "";
    }

    public static void main(String[] args) {
        // Shodan program = new Shodan("Window");

        Roster roster = Roster.importSSPOT(getOpenFilepath());
        Roster.importTrainingQuip(getOpenFilepath(), roster.getScheduledAssociates());

        int count = 0;
        for(Associate a : roster.getScheduledAssociates().values())
            System.out.printf("%d: %s\n", count++, a.toString());
    }
}

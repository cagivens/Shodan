import rosterizer.Roster;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileNotFoundException;

public class Shodan extends JFrame {

    public Shodan(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static String getFilepath() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        int r = chooser.showOpenDialog(null);

        if(r == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return "";
    }

    public static void main(String[] args) {
        // Shodan program = new Shodan("Window");

        Roster roster = Roster.importSSPOT(getFilepath());
    }
}

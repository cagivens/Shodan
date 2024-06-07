import rosterizer.Roster;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Shodan {

    private static String getOpenFilepath() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        int r = chooser.showOpenDialog(null);

        if(r == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return "";
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Shodan");

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exportRoster = new JMenuItem("Export Roster");
        JMenuItem importSSPOT = new JMenuItem("Import SSPOT");

        menuBar.add(file);
        file.add(exportRoster);
        file.add(importSSPOT);
        window.setJMenuBar(menuBar);

        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

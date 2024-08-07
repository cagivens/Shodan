import rosterizer.Roster;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Shodan {

    public static void main(String[] args) {
        JFrame window = new JFrame("Shodan");
        Roster roster = new Roster(List.of("caleigiv", "todpaula", "mickello", "viraubre",
                "idonpowe"));

        List<JButton> buttonList = List.of(new JButton("Import SSPOT"), new JButton("Import Training QUIP"),
                new JButton("Randomize Assignments"), new JButton("Export Roster"));
        buttonList.get(0).addActionListener(e -> roster.importSSPOT(showOpenDialog()));
        buttonList.get(1).addActionListener(e -> roster.importTrainingQUIP(showOpenDialog()));
        buttonList.get(2).addActionListener(e -> roster.randomizeRoles());
        buttonList.get(3).addActionListener(e -> writeBytesToFile(showSaveDialog(), roster.toString().getBytes()));

        window.getContentPane().setLayout(new GridLayout(buttonList.size(), 1));
        for(JButton button : buttonList)
            window.getContentPane().add(button);

        window.setSize(new Dimension(400, 200));
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void loadTrainedRolesInternalFile() {
        // Function checks the local dir for an internal trained roles file
    }

    public static void openRosterizer(Roster roster) {
        JFrame rosterizerWindow = new JFrame();
        rosterizerWindow.setVisible(true);
    }

    public static void initializeWatchdogs() {

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

    public static byte[] readBytesFromFile(String filepath) {
        return new byte[0];
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

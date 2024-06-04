import rosterizer.Roster;

import javax.swing.JFrame;

public class Shodan extends JFrame {

    public Shodan(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Shodan program = new Shodan("Window");

        Roster roster = Roster.importSSPOT("");
    }
}

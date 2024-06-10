import rosterizer.Roster
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*
import javax.swing.filechooser.FileSystemView


fun main() {
    val window = JFrame("Shodan")
    val roster = Roster()
    val sheet = HashMap<Char, Array<JPanel>>()

    // Initializing menu bar
    val menuBar = JMenuBar()
    val fileMenu = JMenu("File")
    val assocMenu = JMenu("Associates")
    val exportRoster = JMenuItem("Export Roster")
    val importSSPOT = JMenuItem("Import SSPOT")
    val importTrainingQuip = JMenuItem("Import Training Quip")
    // Creating menu bar action listeners
    importSSPOT.addActionListener { roster.importSSPOT(showOpenDialog()) }
    importTrainingQuip.addActionListener { roster.importTrainingQUIP(showOpenDialog()) }
    // Adding menu items to menu bar
    menuBar.add(fileMenu)
    menuBar.add(assocMenu)
    fileMenu.add(exportRoster)
    fileMenu.add(importSSPOT)
    assocMenu.add(importTrainingQuip)

    // Setting window parameters
    window.size = Dimension(400, 200)
    window.jMenuBar = menuBar
    window.isResizable = false
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isVisible = true
}

fun openRosterizer(roster: Roster) {
    val rosterizerWindow = JFrame()
    rosterizerWindow.isVisible = true
}

fun showSaveDialog(): String {
    val chooser = JFileChooser(FileSystemView.getFileSystemView())
    val r = chooser.showSaveDialog(null)

    if (r == JFileChooser.APPROVE_OPTION)
        return chooser.selectedFile.absolutePath
    return ""
}
fun showOpenDialog(): String {
    val chooser = JFileChooser(FileSystemView.getFileSystemView())
    val r = chooser.showOpenDialog(null)

    if (r == JFileChooser.APPROVE_OPTION)
        return chooser.selectedFile.absolutePath
    return ""
}
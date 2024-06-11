import fclm.FunctionWatchdog
import fclm.ProcessWatchdog
import fclm.TOTWatchdog
import rosterizer.Roster
import java.awt.Dimension
import javax.swing.*
import javax.swing.filechooser.FileSystemView


fun main() {
    val window = JFrame("Shodan")
    val roster = Roster()
    val sheet = HashMap<Char, Array<JPanel>>()

    // Initializing menu bar
    val menuBar = JMenuBar()
    val fileMenu = JMenu("File")
    val rosterMenu = JMenu("Roster")
    val watchdogMenu = JMenu("Watchdog")
    // Adding menu items to menu bar
    menuBar.add(fileMenu)
    menuBar.add(rosterMenu)
    menuBar.add(watchdogMenu)

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

fun openWatchdogs(function: FunctionWatchdog, proc: ProcessWatchdog, tot: TOTWatchdog) {
    val watchdog = JFrame()
    watchdog.isVisible = true
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
import fclm.FunctionWatchdog
import fclm.ProcessWatchdog
import fclm.TOTWatchdog
import rosterizer.Roster
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*
import javax.swing.filechooser.FileSystemView


fun main() {
    val window = JFrame("Shodan")
    val roster = Roster()

    val helloKello = ""

    window.layout = GridLayout(4, 1)

    // Setting window parameters
    window.size = Dimension(400, 200)
    window.isResizable = false
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isVisible = true
}

fun openRosterizer(roster: Roster) {

}

fun openWatchdogs(function: FunctionWatchdog, proc: ProcessWatchdog, tot: TOTWatchdog) {

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
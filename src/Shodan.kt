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
    val roster = Roster(listOf("caleigiv", "todpaula", "mickello", "viraubre", "idonpowe"))

    val buttonList = listOf(JButton("Import SSPOT"), JButton("Import Training QUIP"),
        JButton("Randomize Assignments"), JButton("Export Roster"))

    buttonList[0].addActionListener { roster.importSSPOT(showOpenDialog()) }
    buttonList[1].addActionListener { roster.importTrainingQUIP(showOpenDialog()) }
    buttonList[2].addActionListener { roster.randomizeRoles() }
    buttonList[3].addActionListener { roster.exportRoster(showSaveDialog()) }

    window.contentPane.layout = GridLayout(buttonList.size, 1)
    for(button in buttonList)
        window.contentPane.add(button)

    //roster.importSSPOT(showOpenDialog())
    //roster.importTrainingQUIP(showOpenDialog())
    //roster.randomizeRoles()

    // Setting window parameters
    window.size = Dimension(400, 200)
    window.isResizable = false
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isVisible = true
}

fun loadTrainedRolesInternalFile() {
    // This should be implemented relatively close to the end of development
    // due to the need for an installer
    TODO("Function checks the local dir for trained indirect roles")
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
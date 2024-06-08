import java.awt.Dimension
import javax.swing.*
import javax.swing.filechooser.FileSystemView


fun main() {
    val window = JFrame("Shodan")
    val menuBar = buildMenuBar()

    window.size = Dimension(800, 600)
    window.jMenuBar = menuBar

    window.isResizable = false
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isVisible = true
}

fun buildMenuBar(): JMenuBar {
    val result = JMenuBar()
    val fileMenu = JMenu("File")
    val exportRoster = JMenuItem("Export Roster")
    val importSSPOT = JMenuItem("Import SSPOT")

    result.add(fileMenu)
    fileMenu.add(exportRoster)
    fileMenu.add(importSSPOT)

    return result
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
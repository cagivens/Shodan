package rosterizer

import java.io.File
import java.io.FileNotFoundException
import java.util.LinkedList
import java.util.Scanner

class Roster {
    private val scheduledAssociates = HashMap<String, Associate>()

    fun importSSPOT(filepath: String = "") {
        if(filepath.isEmpty()) return

        val rows = LinkedList<String>()

        try {
            val file = File(filepath)
            val scanner = Scanner(file)

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine())
            scanner.close()
        } catch(exception: FileNotFoundException) {
            println("FileNotFoundException thrown while importing SSPOT roster")
            return
        }

        rows.removeFirst()
        for(row in rows) {
            val assoc = Associate(row.split(",")[1].replace("\"", ""))
            scheduledAssociates[assoc.login] = assoc
            println("${scheduledAssociates.size}: ${assoc.login}")
        }
    }

    fun importTrainingQUIP(filepath: String = "") {
        if(filepath.isEmpty()) return

        val rows = LinkedList<String>()

        try {
            val file = File(filepath)
            val scanner = Scanner(file)

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine())
            scanner.close()
        } catch(exception: FileNotFoundException) {
            println("FileNotFoundException thrown while importing Training QUIP")
            return
        }

        rows.removeFirst()
        for(row in rows) {
            val rowAsArray = row.substring(row.lastIndexOf('"') + 1, row.length - 1).split(",")
            val loginColValue: String = rowAsArray[1]
            val processTrained: String = rowAsArray[6].lowercase()

            if (scheduledAssociates.containsKey(loginColValue))
                when (processTrained) {
                    "ambassador" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_AMBASSADOR)
                    "audit" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_RECOVERY)
                    "end of line" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_EOL)
                    "outbound" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_OUTBOUND)
                    "pit" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_PIT)
                    "problem solve" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_PS)
                    "refurb" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_REFURB)
                    "shoe icqa" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_ICQA)
                    "shoe processing" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_SHOES)
                    "tdr" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_TDR)
                    "water spider" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_WATERSPIDER)
                    "whd" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_WHD)
                }
        }
    }

    override fun toString(): String {
        return ""
    }
}
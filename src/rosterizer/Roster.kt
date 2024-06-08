package rosterizer

import java.io.File
import java.io.FileNotFoundException
import java.util.*
import kotlin.math.log

class Roster {
    val scheduledAssociates = HashMap<String, Associate>()

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
            println("FileNotFoundException thrown while importting SSPOT roster")
            return
        }

        rows.removeFirst()
        for(row in rows) {
            val assoc = Associate(row.split(",")[1].replace("\"", ""))
            scheduledAssociates[assoc.login] = assoc
            println("${scheduledAssociates.size}: ${assoc.login}")
        }
    }

    fun importTrainingQUIP(filepath: String, associates: HashMap<String, Associate>) {
        if(filepath.isEmpty()) return

        val rows = LinkedList<String>()

        try {
            val file = File(filepath)
            val scanner = Scanner(file)

            while(scanner.hasNextLine())
                rows.add(scanner.nextLine())
            scanner.close()
        } catch(exception: FileNotFoundException) {
            println("FileNotFoundException thrown while importting SSPOT roster")
            return
        }

        rows.removeFirst()
        for(row in rows) {
            val rowAsArray = row.substring(row.lastIndexOf('"') + 1, row.length - 1).split(",")
            val loginColValue: String = rowAsArray[1]
            val processTrained: String = rowAsArray[6].lowercase()

            if (associates.containsKey(loginColValue))
                if (processTrained == "ambassador")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_AMBASSADOR)
                else if (processTrained == "audit")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_RECOVERY)
                else if (processTrained == "end of line")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_EOL)
                else if (processTrained == "outbound")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_OUTBOUND)
                else if (processTrained == "pit")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_PIT)
                else if (processTrained == "problem solve")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_PS)
                else if (processTrained == "refurb")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_REFURB)
                else if (processTrained == "shoe icqa")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_ICQA)
                else if (processTrained == "shoe processing")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_SHOES)
                else if (processTrained == "tdr")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_TDR)
                else if (processTrained == "water spider")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_WATERSPIDER)
                else if (processTrained == "whd")
                    associates[loginColValue]!!.addTrainedRole(Associate.ROLE_WHD)
        }
    }

    override fun toString(): String {
        return ""
    }
}
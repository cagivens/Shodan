package rosterizer

import java.io.File
import java.io.FileNotFoundException
import java.util.LinkedList
import java.util.Scanner

class Roster(private val processAssistants: List<String>) {
    private val scheduledAssociates = HashMap<String, Associate>()
    private val assignedRoles = HashMap<Int, LinkedList<Associate>>()

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

            // Filtering out process assistants from SSPOT roster
            if(processAssistants.contains(assoc.login))
                continue
            scheduledAssociates[assoc.login] = assoc
            //println("${scheduledAssociates.size}: ${assoc.login}")
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

            if (scheduledAssociates.containsKey(loginColValue)) {
                when (processTrained) {
                    "ambassador" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_AMBASSADOR)
                    "audit" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_RECOVERY)
                    "end of line" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_EOL)
                    "outbound" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_OUTBOUND)
                    "pit" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_PIT)
                    "problem solve" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_PS)
                    "refurb" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_REFURB)
                    "shoe icqa" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_ICQA)
                    "tdr" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_TDR)
                    "water spider" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_WATERSPIDER)
                    "whd" -> scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_WHD)
                }
                scheduledAssociates[loginColValue]!!.addTrainedRole(Associate.ROLE_PROCESS)
            }
        }
    }

    fun randomizeRoles() {
        val associateList = scheduledAssociates.keys

        fun assignIndirectRole(role: Int, amount: Int, msg: String) {
            assignedRoles[role] = LinkedList()
            println("===== $msg =====")
            while(assignedRoles[role]!!.size < amount) {
                val rand = (0..<associateList.size).random()

                if(scheduledAssociates[associateList.elementAt(rand)]!!.hasRole(role)) {
                    assignedRoles[role]!!.add(scheduledAssociates[associateList.elementAt(rand)]!!)
                    println("-> ${associateList.elementAt(rand)}")
                    associateList.remove(associateList.elementAt(rand))
                }
            }
        }

        // Assigning indirect roles
        assignIndirectRole(Associate.ROLE_PS, 5, "Problem Solvers")
        assignIndirectRole(Associate.ROLE_WATERSPIDER, 4, "Waterspiders")
        assignIndirectRole(Associate.ROLE_OUTBOUND, 1, "Outbound")
        assignIndirectRole(Associate.ROLE_EOL, 3, "EOL")
        assignIndirectRole(Associate.ROLE_ICQA, 1, "ICQA")
        assignIndirectRole(Associate.ROLE_RECOVERY, 1, "Recovery")
        assignIndirectRole(Associate.ROLE_REFURB, 2, "Refurb")
        assignIndirectRole(Associate.ROLE_WHD, 1, "Warehouse Deals")

        // Assigning whatever associates remain to direct process
        assignedRoles[Associate.ROLE_PROCESS] = LinkedList()
        println("===== Processors =====")
        for(login in associateList) {
            assignedRoles[Associate.ROLE_PROCESS]!!.add(scheduledAssociates[login]!!)
            println("-> $login")
        }
    }

    fun getAssociateLoginsByRole(role: Int): List<String>? = assignedRoles[role]?.map{it.login}

    override fun toString(): String {
        val result = "Problem Solve,,Waterspider,,Outbound,,EOL,,ICQA,,Recovery,,Refurb,,WHD\n"

        var i = 0
        while(i < assignedRoles[Associate.ROLE_PROCESS]!!.size) {

        }

        return result
    }
}
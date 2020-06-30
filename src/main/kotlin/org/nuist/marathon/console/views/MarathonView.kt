package org.nuist.marathon.console.views

import org.nuist.marathon.console.models.MarathonMemStore
import org.nuist.marathon.console.models.MarathonModel

class MarathonView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add a Run")
        println(" 2. Update a Run")
        println(" 3. List All Runs")
        println(" 4. Search a Run")
        println(" 5. Delete a Run")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listRuns(runs: MarathonMemStore) {
        println("List All Runs")
        println()
        runs.logAll()
        println()
    }

    fun showRuns(marathon : MarathonModel) {
        if(marathon != null)
            println("Marathon Details [ $marathon ]")
        else
            println("Mararthon Not Found...")
    }

    fun addMarathonData(marathon : MarathonModel) : Boolean {
        println()
        print("Enter a Run including the place: ")
        marathon.run = readLine()!!
        print("Enter your experience or feeling : ")
        marathon.description = readLine()!!

        return marathon.run.isNotEmpty() && marathon.description.isNotEmpty()
    }

    fun updateMarathonData(marathon : MarathonModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (marathon != null) {
            print("Enter a new Run for [ " + marathon.run + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + marathon.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                marathon.run = tempTitle
                marathon.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}
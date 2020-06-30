package org.nuist.marathon.console.controllers

import mu.KotlinLogging
import org.nuist.marathon.console.models.MarathonMemStore
import org.nuist.marathon.console.models.MarathonModel
import org.nuist.marathon.console.views.MarathonView

class MarathonController {
    val marathons = MarathonMemStore()
    val marathonView = MarathonView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Marathon Run Console App" }
        println("My Marathon Run Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Marathon Run Console App" }
    }

    fun menu() :Int { return marathonView.menu() }

    fun add(){
        var aMarathon = MarathonModel()

        if (marathonView.addMarathonData(aMarathon))
            marathons.create(aMarathon)
        else
            logger.info("Run Not Added")
    }

    fun list() {
        marathonView.listRuns(marathons)
    }

    fun update() {

        marathonView.listRuns(marathons)
        var searchId = marathonView.getId()
        val aMarathon = search(searchId)

        if(aMarathon!= null) {
            if(marathonView.updateMarathonData(aMarathon)) {
                marathons.update(aMarathon)
                marathonView.showRuns(aMarathon)
                logger.info("Marathon Updated : [ $aMarathon ]")
            }
            else
                logger.info("Marathon Not Updated")
        }
        else
            println("Marathon Not Updated...")
    }

    fun delete() {
        marathonView.listRuns(marathons)
        var searchId = marathonView.getId()
        val aMarathon = search(searchId)

        if(aMarathon != null) {
            marathons.delete(aMarathon)
            println("Marathon Deleted...")
            marathonView.listRuns(marathons)
        }
        else
            println("Marathon Not Deleted...")
    }

    fun search() {
        val aMarathon = search(marathonView.getId())!!
        marathonView.showRuns(aMarathon)
    }


    fun search(id: Long) : MarathonModel? {
        var foundMarathon = marathons.findOne(id)
        return foundMarathon
    }

    fun dummyData() {
        marathons.create(MarathonModel(run = "In Shanghai", description = "A enjoyable way to feel city"))
        marathons.create(MarathonModel(run= "In the Sahara Desert", description = "A tantalizing but unforgettable experience"))
        marathons.create(MarathonModel(run = "In London ", description = "Enjoy a lot of places of interest"))
    }
}
package org.nuist.marathon.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L
internal fun getId(): Long {
    return lastId++
}

class MarathonMemStore : MarathonStore {

    val marathons = ArrayList<MarathonModel>()

    override fun findAll(): List<MarathonModel> {
        return marathons
    }

    override fun findOne(id: Long) : MarathonModel? {
        var foundMarathon: MarathonModel? = marathons.find { p -> p.id == id }
        return foundMarathon
    }

    override fun create(marathon: MarathonModel) {
        marathon.id = getId()
        marathons.add(marathon)
        logAll()
    }

    override fun update(marathon: MarathonModel) {
        var foundPlacemark = findOne(marathon.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.run = marathon.run
            foundPlacemark.description = marathon.description
        }
    }

    override fun delete(marathon: MarathonModel) {
        marathons.remove(marathon)
    }

    internal fun logAll() {
        marathons.forEach { logger.info("${it}") }
    }
}
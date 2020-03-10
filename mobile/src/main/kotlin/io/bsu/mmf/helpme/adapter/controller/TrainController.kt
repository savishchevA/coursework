package io.bsu.mmf.helpme.adapter.controller

import com.airbnb.epoxy.EpoxyController
import io.bsu.mmf.helpme.adapter.holders.trainModelView
import io.bsu.mmf.helpme.domain.train.TrainItem
import io.bsu.mmf.helpme.utils.epoxy.EpoxyModelProperty


class TrainController (): EpoxyController() {

    var trains by EpoxyModelProperty { emptyList<TrainItem>() }

    override fun buildModels() {
        trains.forEach { train ->
            trainModelView {
                id(train.name)
                trainName(train.name)
                trainDate(train.day)
                trainDistance(train.distance)
            }
        }
    }
}
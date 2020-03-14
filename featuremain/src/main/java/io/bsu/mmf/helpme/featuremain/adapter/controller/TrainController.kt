package io.bsu.mmf.helpme.featuremain.adapter.controller

import com.airbnb.epoxy.EpoxyController
import io.bsu.mmf.helpme.baseAndroid.utils.epoxy.EpoxyModelProperty
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.featuremain.adapter.holder.trainModelView


class TrainController: EpoxyController() {

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
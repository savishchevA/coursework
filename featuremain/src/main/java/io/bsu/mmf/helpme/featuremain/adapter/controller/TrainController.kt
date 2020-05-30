package io.bsu.mmf.helpme.featuremain.adapter.controller

import com.airbnb.epoxy.EpoxyController
import io.bsu.mmf.helpme.baseAndroid.utils.epoxy.EpoxyModelProperty
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.featuremain.adapter.holder.trainModelView


class TrainController: EpoxyController() {

    var trains by EpoxyModelProperty { emptyList<TrainItem>() }


    var callbacks: Callbacks? = null

    interface Callbacks {
        fun onTrainClick(trainItem: TrainItem)
    }
    override fun buildModels() {
        trains.forEach { train ->
            trainModelView {
                id(train.id)
                trainName("Тренирока №${train.id}")
                trainDate(train.time)
                trainDistance(train.distance)
                onTrainClickListener {
                    callbacks?.onTrainClick(train)
                }
            }
        }
    }
}
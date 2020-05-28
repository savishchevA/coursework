package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.data.train.TrainItem

class TrainListViewModel : BaseViewModel() {
    private val _trainList = MutableLiveData<List<TrainItem>>()
    val trainList: LiveData<List<TrainItem>>
        get() = _trainList
    private val trainListData = emptyList<TrainItem>()
//        private val trainListData = listOf<TrainItem>(
//        TrainItem(
//            name = "Тренировка №1",
//            day = "20.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №2",
//            day = "21.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:50"
//        ), TrainItem(
//            name = "Тренировка №3",
//            day = "22.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №4",
//            day = "23.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:15"
//        ), TrainItem(
//            name = "Тренировка №5",
//            day = "25.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №6",
//            day = "20.05",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №7",
//            day = "20.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        )
//    )

    init {
        _trainList.value = trainListData
    }
}
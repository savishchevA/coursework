package io.bsu.mmf.helpme.presenter.main

//import io.bsu.mmf.helpme.data.usecase.weather.GetCurrentWeatherUseCase
//import io.bsu.mmf.helpme.domain.train.TrainItem
//import io.bsu.mmf.helpme.domain.usecase.FetchContactUseCase
//import io.bsu.mmf.helpme.domain.usecase.FetchMessageUseCase
//import io.bsu.mmf.helpme.domain.usecase.FetchTimeUseCase
//
//import io.bsu.mmf.helpme.view.main.MainView
//import kotlinx.coroutines.launch
//import moxy.InjectViewState
//
//
//
//@InjectViewState
//class MainActivityPresenter (
//        private val fetchContact: FetchContactUseCase,
//        private val fetchMessage: FetchMessageUseCase,
//        private val fetchTime: FetchTimeUseCase,
//        private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
//) : BasePresenter<MainView>() {
//
//    val trainList = listOf<TrainItem>(
//            TrainItem(
//                    name = "Тренировка №1",
//                    day = "20/10/20",
//                    distance = "14.55",
//                    status = "Complete",
//                    time = "14:55"
//            ), TrainItem(
//            name = "Тренировка №2",
//            day = "21/10/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:50"
//    ), TrainItem(
//            name = "Тренировка №3",
//            day = "22/10/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//    ), TrainItem(
//            name = "Тренировка №4",
//            day = "23/10/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:15"
//    ), TrainItem(
//            name = "Тренировка №5",
//            day = "25/10/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//    ), TrainItem(
//            name = "Тренировка №6",
//            day = "20/05/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//    ), TrainItem(
//            name = "Тренировка №7",
//            day = "20/10/20",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//    )
//    )
//
//    fun getCurrentWeather() {
//        localRepositoryScope.launch {
//            getCurrentWeatherUseCase().fold(
//                    onSuccess = {
//                        viewState.setCurrentWeather(it)
//                    }
//            )
//
//        }
//    }
//
//    fun fetchData() {
////        val contact = fetchContact.execute()
////        val message = fetchMessage.execute()
////        viewState?.setCurrentConfig(contact!!, message!!)
//    }
//
//    fun saveTime(time: Int) {
////         fetchTime.setTime(time)
////         viewState?.navigateToMap()
//    }
//}

//package io.bsu.mmf.helpme.domain.usecase
//
//import io.bsu.mmf.helpme.data.entity.ActivityType
//import io.bsu.mmf.helpme.domain.repository.LocationRepository
//import io.reactivex.Maybe
//import timber.log.Timber
//
//
//
//
//
//class AlertOnUserIsStillUseCase (private val locationRepository: LocationRepository) {
//
//    fun execute(): Maybe<ActivityType> {
//        return locationRepository.detectStillness()
//                .doOnNext { Timber.v("User is still for long time") }
//                .firstElement()
//    }
//}

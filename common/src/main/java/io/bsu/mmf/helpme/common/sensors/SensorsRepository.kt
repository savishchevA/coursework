package io.bsu.mmf.helpme.common.sensors

import io.bsu.mmf.helpme.data.entity.ActivityType
import io.bsu.mmf.helpme.data.entity.Coordinates
import io.bsu.mmf.helpme.data.entity.TimePeriod
import io.bsu.mmf.helpme.common.database.service.LocalRepository
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber



class SensorsRepository (
        private val activityDetectionService: ActivityDetectionService,
        private val timeToTellStillness: TimePeriod,
        private val locationService: LocationService,
        private val localRepository: LocalRepository
) : LocationRepository {


   // private val fetchTime = FetchTimeUseCase(localRepository)

//    override fun detectStillness(): Observable<ActivityType> {
//        Timber.e("Current time: ")
////        Timber.e("Current time: ${fetchTime.getTime()}")
////        return activityDetectionService.userMovingUpdates()
////                .distinctUntilChanged()
////                .debounce(fetchTime.getTime()?.toLong() ?: 3, timeToTellStillness.unit)
////                .filter { it == ActivityType.STILL }
//    }
//
//    override fun getCurrentCoordinates(): Single<Coordinates> {
//        return locationService.current()
//    }

}

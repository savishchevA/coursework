package io.bsu.mmf.helpme.data.sensors

import io.bsu.mmf.helpme.domain.entity.ActivityType
import io.bsu.mmf.helpme.domain.entity.Coordinates
import io.bsu.mmf.helpme.domain.entity.TimePeriod
import io.bsu.mmf.helpme.domain.repository.LocalRepository
import io.bsu.mmf.helpme.domain.repository.LocationRepository
import io.bsu.mmf.helpme.domain.usecase.FetchTimeUseCase
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject


class SensorsRepository @Inject constructor(
        private val activityDetectionService: ActivityDetectionService,
        private val timeToTellStillness: TimePeriod,
        private val locationService: LocationService,
        private val localRepository: LocalRepository
) : LocationRepository {


    private val fetchTime = FetchTimeUseCase(localRepository)

    override fun detectStillness(): Observable<ActivityType> {
        Timber.e("Current time: ${fetchTime.getTime()}")
        return activityDetectionService.userMovingUpdates()
                .distinctUntilChanged()
                .debounce(fetchTime.getTime()?.toLong() ?: 3, timeToTellStillness.unit)
                .filter { it == ActivityType.STILL }
    }

    override fun getCurrentCoordinates(): Single<Coordinates> {
        return locationService.current()
    }

}

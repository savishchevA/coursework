package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.entity.Coordinates
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.github.zeyomir.extremesportssos.domain.usecase.FetchTimeUseCase
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

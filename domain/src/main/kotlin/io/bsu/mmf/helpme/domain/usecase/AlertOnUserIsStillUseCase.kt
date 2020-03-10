package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.entity.ActivityType
import io.bsu.mmf.helpme.domain.repository.LocationRepository
import io.reactivex.Maybe
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlertOnUserIsStillUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    fun execute(): Maybe<ActivityType> {
        return locationRepository.detectStillness()
                .doOnNext { Timber.v("User is still for long time") }
                .firstElement()
    }
}

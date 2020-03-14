package io.bsu.mmf.helpme.common.sensors.service

import android.location.Location
import io.bsu.mmf.helpme.common.sensors.LocationService
import io.bsu.mmf.helpme.data.entity.Coordinates
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber



class PlayServicesLocationService (private val observable: Observable<Location>) : LocationService {
    override fun current(): Single<Coordinates> {
        return observable
                .map { Coordinates(it.latitude, it.longitude) }
                .doOnNext { Timber.v("Retrieved current location: %f | %f", it.latitude, it.longitude) }
                .firstOrError()
    }
}

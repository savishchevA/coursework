package io.bsu.mmf.helpme.domain.repository

import io.bsu.mmf.helpme.domain.entity.ActivityType
import io.bsu.mmf.helpme.domain.entity.Coordinates
import io.reactivex.Observable
import io.reactivex.Single


interface LocationRepository {
    fun detectStillness(): Observable<ActivityType>
    fun getCurrentCoordinates(): Single<Coordinates>
}

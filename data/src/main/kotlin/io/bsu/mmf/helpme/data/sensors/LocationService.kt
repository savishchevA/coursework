package io.bsu.mmf.helpme.data.sensors

import io.bsu.mmf.helpme.domain.entity.Coordinates
import io.reactivex.Single


interface LocationService {
    fun current(): Single<Coordinates>
}

package io.bsu.mmf.helpme.common.sensors

import io.bsu.mmf.helpme.data.entity.Coordinates
import io.reactivex.Single


interface LocationService {
    fun current(): Single<Coordinates>
}

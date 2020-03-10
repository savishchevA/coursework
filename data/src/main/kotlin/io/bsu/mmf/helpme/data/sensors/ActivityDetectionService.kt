package io.bsu.mmf.helpme.data.sensors

import io.bsu.mmf.helpme.domain.entity.ActivityType
import io.reactivex.Observable


interface ActivityDetectionService {
    fun userMovingUpdates(): Observable<ActivityType>
}

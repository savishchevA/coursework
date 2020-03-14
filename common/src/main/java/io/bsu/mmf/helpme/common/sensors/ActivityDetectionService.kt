package io.bsu.mmf.helpme.common.sensors

import io.bsu.mmf.helpme.data.entity.ActivityType
import io.reactivex.Observable


interface ActivityDetectionService {
    fun userMovingUpdates(): Observable<ActivityType>
}

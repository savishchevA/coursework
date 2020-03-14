package io.bsu.mmf.helpme.common.sensors.service

import com.google.android.gms.location.DetectedActivity
import io.bsu.mmf.helpme.common.sensors.ActivityDetectionService
import io.bsu.mmf.helpme.data.entity.ActivityType
import io.reactivex.Observable
import timber.log.Timber



class PlayServicesActivityDetectionService (private val acivityObservable: Observable<DetectedActivity>) : ActivityDetectionService {

    override fun userMovingUpdates(): Observable<ActivityType> {
        return acivityObservable
                .map { if (it.type == DetectedActivity.STILL) ActivityType.STILL else ActivityType.MOVING }
                .doOnNext { Timber.v("User is %s", it) }
    }
}
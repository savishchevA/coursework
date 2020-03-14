//package io.bsu.mmf.helpme.domain.usecase
//
//import io.bsu.mmf.helpme.data.driver.SmsDriver
//import io.bsu.mmf.helpme.domain.repository.LocalRepository
//import io.bsu.mmf.helpme.domain.repository.LocationRepository
//import io.reactivex.Completable
//
//
//
//class SendSosMessageUseCase (
//        private val localRepository: LocalRepository,
//        private val locationRepository: LocationRepository,
//        private val smsDriver: SmsDriver
//) {
//    fun execute(): Completable {
//        val contact = localRepository.fetchContact()
//        return locationRepository.getCurrentCoordinates()
//                .map { "https://www.google.com/maps/search/?api=1&query=${it.latitude},${it.longitude}" }
//                .map { "${localRepository.fetchMessage()}\n$it" }
//                .flatMapCompletable { smsDriver.sendSms(contact!!, it) }
//    }
//}

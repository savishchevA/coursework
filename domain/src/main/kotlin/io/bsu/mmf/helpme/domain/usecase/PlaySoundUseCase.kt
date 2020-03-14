//package io.bsu.mmf.helpme.domain.usecase
//
//import io.bsu.mmf.helpme.data.driver.SoundDriver
//
//
//
//
//
//class PlaySoundUseCase (private val soundDriver: SoundDriver) {
//    private var prepared = false
//
//    fun prepare() {
//        if (prepared) return
//        soundDriver.prepare()
//        prepared = true
//    }
//
//    fun execute() {
//        if (prepared) {
//            soundDriver.playSound()
//        }
//    }
//
//    fun finish() {
//        if (!prepared) return
//        soundDriver.finish()
//        prepared = false
//    }
//}

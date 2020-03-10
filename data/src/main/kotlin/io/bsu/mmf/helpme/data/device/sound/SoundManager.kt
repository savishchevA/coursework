package io.bsu.mmf.helpme.data.device.sound

import io.bsu.mmf.helpme.domain.driver.SoundDriver



class SoundManager (private val soundService: SoundService, private val soundId: Int) : SoundDriver {
    private var currentVolume: Int = -1

    override fun playSound() {
        soundService.playSound()
    }

    override fun prepare() {
        if (currentVolume != -1) return
        currentVolume = soundService.getCurrentVolume()
        soundService.setToMaxVolume()
        soundService.prepare(soundId)
    }

    override fun finish() {
        if (currentVolume == -1) return
        soundService.setToVolume(currentVolume)
        currentVolume = -1
        soundService.release()
    }
}

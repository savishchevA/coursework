package io.bsu.mmf.helpme.common.device.sound


interface SoundService {
    fun prepare(soundId: Int)
    fun release()

    fun getCurrentVolume(): Int
    fun setToMaxVolume()
    fun setToVolume(volume: Int)

    fun playSound()
}

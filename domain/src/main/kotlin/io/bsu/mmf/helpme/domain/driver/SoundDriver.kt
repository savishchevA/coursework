package io.bsu.mmf.helpme.domain.driver


interface SoundDriver {
    fun playSound()
    fun prepare()
    fun finish()
}

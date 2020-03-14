package io.bsu.mmf.helpme.common.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int,
    @SerialName("speed")
    val speed: Int,

    @SerialName("gust")
    val gust: Int
)
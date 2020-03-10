package io.bsu.mmf.helpme.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double
)
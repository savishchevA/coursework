package io.bsu.mmf.helpme.common.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    val lat: Double? = null,

    @Json(name = "lon")
    val lon: Double? = null
)
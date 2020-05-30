package io.bsu.mmf.helpme.common.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name="deg")
    val deg: Int? = null,

    @Json(name="speed")
    val speed: Double? = null,

    @Json(name="gust")
    val gust: Int? = null
)
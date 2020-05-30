package io.bsu.mmf.helpme.common.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class Sys(

    @Json(name="country")
    val country: String? = null,

    @Json(name="id")
    val id: Int? = null,

    @Json(name="sunrise")
    val sunrise: Int? = null,

    @Json(name="sunset")
    val sunset: Int? = null,

    @Json(name="type")
    val type: Int? = null
)
package io.bsu.mmf.helpme.common.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@JsonClass(generateAdapter = true)
data class Weather(

    @Json(name="description")
    val description: String? = null,

    @Json(name="icon")
    val icon: String? = null,

    @Json(name="id")
    val id: Int? = null,

    @Json(name="main")
    val main: String? = null
)
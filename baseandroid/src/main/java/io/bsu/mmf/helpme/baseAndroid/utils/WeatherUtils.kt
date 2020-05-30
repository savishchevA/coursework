package io.bsu.mmf.helpme.baseAndroid.utils

import android.content.Context
import android.graphics.drawable.Drawable
import io.bsu.mmf.helpme.baseAndroid.R
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    val density = this.resources
        .displayMetrics
        .density
    return (dp.toFloat() * density).roundToInt()
}

fun Context.getWeatherIcon(weatherCode: String): Drawable? {

    return when (weatherCode) {
        "01d" -> {
            this.getDrawable(R.drawable.d01)
        }
        "01n" -> {
            this.getDrawable(R.drawable.n01)
        }
        "02d" -> {
            this.getDrawable(R.drawable.d02)
        }
        "02n" -> {
            this.getDrawable(R.drawable.n02)
        }
        "03n" -> {
            this.getDrawable(R.drawable.n03)
        }
        "03d" -> {
            this.getDrawable(R.drawable.d03)
        }
        "04d" -> {
            this.getDrawable(R.drawable.d04)
        }
        "04n" -> {
            this.getDrawable(R.drawable.n04)
        }
        "09d" -> {
            this.getDrawable(R.drawable.n09)
        }
        "09n" -> {
            this.getDrawable(R.drawable.n09)
        }
        "10d" -> {
            this.getDrawable(R.drawable.d10)
        }
        "10n" -> {
            this.getDrawable(R.drawable.n10)
        }
        "11d" -> {
            this.getDrawable(R.drawable.d11)
        }
        "11n" -> {
            this.getDrawable(R.drawable.n11)
        }
        "13d" -> {
            this.getDrawable(R.drawable.d13)
        }
        "13n" -> {
            this.getDrawable(R.drawable.n13)
        }
        "50d" -> {
            this.getDrawable(R.drawable.d50)
        }
        "50n" -> {
            this.getDrawable(R.drawable.n50)
        }

        else -> {
            this.getDrawable(R.drawable.d02)
        }
    }
}
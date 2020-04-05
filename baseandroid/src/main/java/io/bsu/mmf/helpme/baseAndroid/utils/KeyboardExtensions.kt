package io.bsu.mmf.helpme.baseAndroid.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

val Context.keyboard: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Activity.keyboard: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Activity.showKeyboard() = keyboard.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

fun Activity.hideKeyboard() = keyboard.hideSoftInputFromWindow(window.decorView.windowToken, 0)
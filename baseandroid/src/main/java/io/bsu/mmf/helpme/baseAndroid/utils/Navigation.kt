package io.bsu.mmf.helpme.baseAndroid.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.*

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId)
    if (action != null) navigate(resId, args, navOptions, navExtras)
}

fun NavController.navigateDirectionSafe(
    directions: NavDirections,
    navOptions: NavOptions? = null
) {
    val action = currentDestination?.getAction(directions.actionId)
    if (action != null) navigate(directions, navOptions)
}
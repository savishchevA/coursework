package io.bsu.mmf.helpme.baseAndroid.utils.dialog

import android.annotation.TargetApi
import android.os.Build
import android.view.*
import androidx.annotation.RequiresApi
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

@RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
@TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
class SimpleKeyboardAnimator(window: Window) : BaseKeyboardAnimator(window) {

    private val sceneRoot: ViewGroup? by lazy(LazyThreadSafetyMode.NONE) {
        window.decorView.findViewById<View>(Window.ID_ANDROID_CONTENT)?.parent as? ViewGroup
    }

    override val insetsListener: View.OnApplyWindowInsetsListener
        get() = View.OnApplyWindowInsetsListener { view, insets ->
            sceneRoot?.let { TransitionManager.beginDelayedTransition(it, ChangeBounds()) }
            return@OnApplyWindowInsetsListener view.onApplyWindowInsets(insets)
        }
}
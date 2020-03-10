package io.bsu.mmf.helpme.fragments.auth

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationSet
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.auth.AuthPresenter
import io.bsu.mmf.helpme.view.auth.AuthView
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject

class AuthFragment : BaseFragment(), AuthView {
    @Inject
    lateinit var daggerPresenter: Lazy<AuthPresenter>

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter = daggerPresenter.get()


    override val layout: Int
        get() = R.layout.fragment_auth
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance();

    var isChanged = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            navController.navigate(R.id.authFragment_to_loginFragment)
        }

        btn_registration.setOnClickListener {
            navController.navigate(R.id.authFragment_to_registrationFragment)
        }
    }

    private fun setAnimation() {

    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(requireContext(), id)
        newConstraintSet.applyTo(root)
        TransitionManager.beginDelayedTransition(root)
    }

    private fun slideView(view: View, currentHeight: Int, newHeight: Int) {
        val slideAnimator = ValueAnimator
                .ofInt(currentHeight, newHeight)
                .setDuration(1000)

        slideAnimator.addUpdateListener {
            val value: Int = it.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }

        val animationSet = AnimatorSet()
        animationSet.apply {
            interpolator = AccelerateDecelerateInterpolator()
            play(slideAnimator)
            start()
        }
    }
}
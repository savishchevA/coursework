package io.bsu.mmf.helpme.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import io.bsu.mmf.helpme.R
import kotlinx.android.synthetic.main.fragment_sos_message.*


class SosMessageFragment : BaseFragment() {


//    @Inject
//    lateinit var daggerPresenter: Lazy<SosMessagePresenter>
//
//    @InjectPresenter
//    lateinit var presenter: SosMessagePresenter
//
//
//    @ProvidePresenter
//    fun providePresenter(): SosMessagePresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.fragment_sos_message
//    override val basePresenter: BasePresenter<*>?
//        get() = presenter

    override fun onStart() {
        super.onStart()
//        requireActivity().window.statusBarColor=
//                ContextCompat.getColor(requireContext(), android.R.color.transparent)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val gd = GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
//                Color.parseColor("#CB356B"),
//                Color.parseColor("#BD3F32")))
//        gd.cornerRadius = 0f
//        gd.gradientType = GradientDrawable.LINEAR_GRADIENT
//
//
//        container.background = gd

        val animDrawable = container.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }

}
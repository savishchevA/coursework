package io.bsu.mmf.helpme.featuremain.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.SmsManager
import android.view.View
import android.view.animation.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.navigateSafe
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.ActiveScanningViewModel
import kotlinx.android.synthetic.main.fragment_active_scanning.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import timber.log.Timber

class ActiveScanningFragment : BaseFragment(R.layout.fragment_active_scanning) {

    private val viewModel by inject<ActiveScanningViewModel>()

    private lateinit var timer: CountDownTimer

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), android.R.color.transparent
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel.startTimer()


       // Timber.e("Current circle bottom : ${iv_cirle.} top: ${iv_cirle.top}")
      //  startTimeAnimation(9)

//        viewModel.currentTimerValue.observe(viewLifecycleOwner, Observer {
//            startTimeAnimation(it)
//            if (it == 1) {
//             //   sendMessage()
//              //  navController.navigateUp()
//
//            }
//        })
        startTimer()
        waveView.play()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(11000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
               // setTimerTime(millisUntilFinished / 1000)
                startTimeAnimation((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                navController.navigateSafe(R.id.showHelpFragment)
            }
        }
        timer.start()
    }

    private fun sendMessage() {

//        val smsManager = SmsManager.getDefault()
//
//        val dividedMessage = smsManager.divideMessage("test message")
//        smsManager.sendMultipartTextMessage("+375291196847", null,dividedMessage , null, null)

    }

    //TODO: replace const values
    private fun startTimeAnimation(time: Int) {

        tv_timer.text = time.toString()
        val animation1 = AlphaAnimation(0.0f, 1.0f)
        animation1.setDuration(500)
        animation1.setStartOffset(0)
        animation1.setFillAfter(false)

        val animation2 = AlphaAnimation(1.0f, 0.0f)
        animation2.setDuration(500)
        animation2.setStartOffset(500)
        animation2.setFillAfter(false)
        val anim = AnimationSet(false)

        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            tv_timer.height.toFloat() -80f,  // fromYDelta
            tv_timer.height.toFloat() - 380f
        )
        animate.setDuration(1000)
        animate.setFillAfter(false)
        anim.addAnimation(
            animation1
        )
        anim.addAnimation(animation2)
        anim.addAnimation(animate)



          tv_timer.startAnimation(anim)
      //  slideUp(tv_timer)


    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            tv_timer.bottom.toFloat(),  // fromYDelta
            iv_cirle.top.toFloat()
        ) // toYDelta
        animate.setDuration(1000)
        animate.setFillAfter(true)
        view.startAnimation(animate)
    }


}
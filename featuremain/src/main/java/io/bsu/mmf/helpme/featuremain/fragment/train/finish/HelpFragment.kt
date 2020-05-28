package io.bsu.mmf.helpme.featuremain.fragment.train.finish

import android.Manifest
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.HelpViewModel
import kotlinx.android.synthetic.main.fragment_help.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HelpFragment : BaseFragment(R.layout.fragment_help) {

    val viewModel by viewModel<HelpViewModel>()
    private lateinit var v: Vibrator
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.help_train
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

       // v.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 150) , 1))

        val rotate: ObjectAnimator = ObjectAnimator.ofFloat(
            btn_call,
            "rotation",
            0f,
            5f,
            0f,
            -5f,
            0f
        ) // rotate o degree then 20 degree and so on for one loop of rotation.

// animateView (View object)
        // animateView (View object)
        rotate.repeatCount = 100 // repeat the loop 20 times
        rotate.duration = 100 // animation play time 100 ms
        rotate.start()

        btn_call.setOnClickListener {
            callToMedical()
        }
    }

    private fun callToMedical() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:+375291196847") //this is the phone number calling

        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.CALL_PHONE),  //request specific permission from user
                10
            )
            return
        } else {     //have got permission
            try {
                startActivity(callIntent) //call activity and make phone call
            } catch (ex: ActivityNotFoundException) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::v.isInitialized) {
            v.cancel()
        }

    }

}
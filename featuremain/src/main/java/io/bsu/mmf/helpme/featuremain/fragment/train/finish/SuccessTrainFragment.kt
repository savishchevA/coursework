package io.bsu.mmf.helpme.featuremain.fragment.train.finish

import android.os.Bundle
import android.view.View
import android.view.animation.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.navigateSafe
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.SuccessTrainViewModel
import kotlinx.android.synthetic.main.fragment_success_train.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuccessTrainFragment : BaseFragment(R.layout.fragment_success_train) {


    private val viewModel by viewModel<SuccessTrainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_success_train.animate()
            .translationY(100f)
            .alpha(1f)
            .setInterpolator(AccelerateInterpolator())
            .setInterpolator(BounceInterpolator())
            .setDuration(1000)
            .startDelay = 500

        btn_continue.animate()
            .translationYBy(-50f)
            .alpha(1f)
            .setInterpolator(AccelerateInterpolator())
            .setInterpolator(BounceInterpolator())
            .setDuration(1000)
            .startDelay = 1000

        btn_continue.setOnClickListener {
            navController.navigateSafe(R.id.action_global_mainFragment)
        }
    }

}
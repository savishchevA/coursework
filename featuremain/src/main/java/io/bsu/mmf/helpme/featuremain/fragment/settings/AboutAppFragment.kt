package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import kotlinx.android.synthetic.main.fragment_about_app.*

class AboutAppFragment : BaseFragment(R.layout.fragment_about_app) {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.main_text_color
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_continue.setOnClickListener {
            navController.navigateUp()
        }
    }

}
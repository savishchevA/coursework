package io.bsu.mmf.helpme.featuremain.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.SendMessageViewModel
import org.koin.android.ext.android.inject

class SendMessageFragment : BaseFragment(R.layout.fragment_send_message) {

    private val viewModel by inject<SendMessageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
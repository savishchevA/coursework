package io.bsu.mmf.helpme.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.view.main.MainActivity

//class SendMessageFragment : io.bsu.mmf.helpme.baseAndroid.BaseFragment() {
//
//
////    @Inject
////    lateinit var daggerPresenter: Lazy<SendMessageActivityPresenter>
////
////    @InjectPresenter
////    lateinit var presenter: SendMessageActivityPresenter
////
////    @ProvidePresenter
////    fun providePresenter(): SendMessageActivityPresenter = daggerPresenter.get()
//
//    override val layout: Int
//        get() = R.layout.activity_send_message
////    override val basePresenter: BasePresenter<*>?
////        get() = presenter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        fine.setOnClickListener {
//            val i = Intent(requireContext(), MainActivity::class.java)
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(i)
//        }
//
//
//    }
////    override fun showSending() {
////        sending_status.text = getString(R.string.send_message_sending)
////    }
////
////    override fun showSent() {
////        sending_status.text = getString(R.string.send_message_sent)
////        val done = ContextCompat.getDrawable(requireContext(), R.drawable.ic_done)
////        sending_status.setCompoundDrawables(null, null, done, null)
////    }
//}
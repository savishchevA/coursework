package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.R
//
//class WelcomeFragment : io.bsu.mmf.helpme.baseAndroid.BaseFragment() {
//
////    @Inject
////    lateinit var daggerPresenter: Lazy<WelcomeActivityPresenter>
////
////    @InjectPresenter
////    lateinit var presenter: WelcomeActivityPresenter
////
////    @ProvidePresenter
////    fun providePresenter(): WelcomeActivityPresenter = daggerPresenter.get()
//
//    override val layout: Int
//        get() = R.layout.activity_welcome
////    override val basePresenter: BasePresenter<*>?
////        get() = presenter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //presenter.checkConfig()
//    }
//
////    override fun showConfigNeededMessage() {
////        view?.visibility = View.VISIBLE
////        button.setOnClickListener {
////            navController.navigate(R.id.action_welcomeFragment_to_configureContactFragment)
////        }
////    }
////
////    override fun goToNextScreen() {
////        navController.navigate(R.id.action_welcomeFragment_to_mainFragment)
////    }
//}
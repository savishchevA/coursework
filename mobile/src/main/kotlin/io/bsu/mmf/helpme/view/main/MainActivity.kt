package io.bsu.mmf.helpme.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.inject


class MainActivity :BaseActivity() {

   private val viewModel by inject<MainActivityViewModel>()

    override val layout: Int
        get() = R.layout.activity_main


    private lateinit var host: NavHostFragment

    private lateinit var navController: NavController
    private lateinit var graph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        host = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return

        navController = host.navController


        viewModel.registrationStatus.observe(this, Observer {
            updateUserLoginStatus(it)
        })

        viewModel.checkUserLogin.observe(this, Observer {
            checkRegistrationStatus(it)
        })

//        val inflater = navController.navInflater
//
//        graph = inflater.inflate(R.navigation.nav_graph_main)
//
//       host.navController.graph = graph
    }

    fun updateUserLoginStatus(isNewUser: Boolean) {

        val inflater = navController.navInflater

        graph = inflater.inflate(R.navigation.nav_graph_main)

        if (isNewUser) {
            graph.startDestination = R.id.authFragment
        } else {
            graph.startDestination = R.id.loginFragment
        }
        host.navController.graph = graph
    }

    fun checkRegistrationStatus(registrationStatus: Boolean) {

        val inflater = navController.navInflater

        graph = inflater.inflate(R.navigation.nav_graph_main)

        if(registrationStatus) {
            graph.startDestination = R.id.mainFragment
        } else {
            graph.startDestination = R.id.authFragment
        }
        host.navController.graph = graph
    }

    //
//    @Inject
//    lateinit var presenter: MainPresenter
//
//    private lateinit var dialog: ChooseTimeDialogFragment
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        presenter.bind(this)
//        presenter.fetchData()
//        start.setOnClickListener {
//
//            openChooseTimeDialog()
//        }
//        config.setOnClickListener {
//            val i = Intent(this, ConfigureContactActivity::class.java)
//            startActivity(i)
//        }
//        setupWindowAnimations()
//
//        EventBus.getDefault().register(this)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.unbind()
//        EventBus.getDefault().unregister(this)
//    }
//
//    private fun setupWindowAnimations() {
//        val slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
//        window.exitTransition = slide
//    }
//
//    override fun navigateToMap() {
//          val i = Intent(this, MapActivity::class.java)
//             startActivity(i)
//    }
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    fun onEvent(event: EventCode) {
//            presenter.saveTime(event.code)
//    }
//
//    private fun openChooseTimeDialog() {
//        dialog = ChooseTimeDialogFragment.newInstance()
//        dialog.show(supportFragmentManager,
//                "add_photo_dialog_fragment")
//    }
//
//    override fun setCurrentConfig(contact: SosContact, message: String) {
//        contact_info.text = getString(R.string.main_contact)
//        sos_message.text = message
//        name.text = contact.contactName
//        phone.text = contact.contactInfo
//    }
//
//    private fun parseContact(contact: SosContact): String {
//        return if (contact.contactName.isNullOrEmpty()) {
//            contact.contactInfo
//        } else {
//            "${contact.contactName} (${contact.contactInfo})"
//        }
//    }
}

abstract class  BaseActivity : AppCompatActivity() {

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(layout)
    }

}

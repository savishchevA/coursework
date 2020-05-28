package io.bsu.mmf.helpme.view.main

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.utils.observeEvent
import io.bsu.mmf.helpme.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainActivityViewModel>()


    private lateinit var host: NavHostFragment

    private lateinit var navController: NavController
    private lateinit var graph: NavGraph


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(
            this, R.color.transparentStatusBar
        )

        host = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        NavigationUI.setupWithNavController(bottomNavigationView, host.navController)

        navController = host.navController

        host.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment,
                R.id.contactsFragment,
                R.id.settingsFragment -> showBottomNavigation()
                else -> hideBottomNavigation()
            }
        }

        viewModel.registrationStatus.observe(this, Observer {
            updateUserLoginStatus(it)
        })

        viewModel.checkUserLogin.observe(this, Observer {
            //checkRegistrationStatus(it)
        })

        viewModel.successLogin.observeEvent(this) {
            Timber.e("From activity registration")
        }



//        val inflater = navController.navInflater
//
//        graph = inflater.inflate(R.navigation.nav_graph_main)
//
//       host.navController.graph = graph
    }


    private fun hideBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
       // bottomNavigationView.visibility = View.VISIBLE
    }

    private fun updateUserLoginStatus(isCompleteRegistration: Boolean) {

        val inflater = navController.navInflater

        graph = inflater.inflate(R.navigation.nav_graph_main)

        if (isCompleteRegistration) {
            graph.startDestination = R.id.mainFragment
        } else {
            graph.startDestination = R.id.authMainFragment
        }
        host.navController.graph = graph
    }

    fun checkRegistrationStatus(registrationStatus: Boolean) {

        val inflater = navController.navInflater

        graph = inflater.inflate(R.navigation.nav_graph_main)

        if (registrationStatus) {
            graph.startDestination = R.id.mainFragment
        } else {
            graph.startDestination = R.id.authMainFragment
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

fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

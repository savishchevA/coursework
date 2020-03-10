package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.data.ContactFb
import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.auth.RegistrationSecondPresenter
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.view.auth.RegistrationSecondView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_registration_second.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject


class RegistrationSecondFragment : BaseFragment(), RegistrationSecondView {
    @Inject
    lateinit var daggerPresenter: Lazy<RegistrationSecondPresenter>

    @InjectPresenter
    lateinit var presenter: RegistrationSecondPresenter

    @ProvidePresenter
    fun providePresenter(): RegistrationSecondPresenter = daggerPresenter.get()


    override val layout: Int
        get() = R.layout.fragment_registration_second
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_continue.setOnClickListener {
            presenter.saveContact(Contact(
                    name = contact_name.text,
                    phoneNumber = contact_phone.text,
                    message = contact_message.text,
                    address = contact_address.text,
                    isPriorityContact = true
            ))
        }

        btn_late.setOnClickListener {
            navigateToMainScreen()
        }
    }

    override fun navigateToMainScreen() {
        navController.navigate(R.id.action_global_mainFragment)
    }
}
package io.github.zeyomir.extremesportssos.view.main

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import io.github.zeyomir.extremesportssos.view.map.MapActivity
import io.github.zeyomir.extremesportssos.view.map.dialog.ChooseTimeDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var dialog: ChooseTimeDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        presenter.fetchData()
        start.setOnClickListener {
            val i = Intent(this, MapActivity::class.java)
            startActivity(i)
            //openChooseTimeDialog()
        }
        config.setOnClickListener {
            val i = Intent(this, ConfigureContactActivity::class.java)
            startActivity(i)
        }
        setupWindowAnimations()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }
    private fun setupWindowAnimations() {
        val slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
        window.exitTransition = slide
    }

    private fun openChooseTimeDialog() {
        dialog = ChooseTimeDialogFragment.newInstance()
        dialog.show(supportFragmentManager,
                "add_photo_dialog_fragment")
    }

    override fun setCurrentConfig(contact: SosContact, message: String) {
        contact_info.text = getString(R.string.main_contact)
        sos_message.text = message
        name.text = contact.contactName
        phone.text = contact.contactInfo
    }

    private fun parseContact(contact: SosContact): String {
        return if (contact.contactName.isNullOrEmpty()) {
            contact.contactInfo
        } else {
            "${contact.contactName} (${contact.contactInfo})"
        }
    }
}

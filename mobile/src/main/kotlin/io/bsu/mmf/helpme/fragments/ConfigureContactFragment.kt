package io.bsu.mmf.helpme.fragments

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.contact.ConfigureContactActivityPresenter
import io.bsu.mmf.helpme.view.contact.ConfigureContactView
import kotlinx.android.synthetic.main.activity_contact.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ConfigureContactFragment : BaseFragment(), ConfigureContactView {

    private val SELECT_PHONE_NUMBER: Int = 1

    @Inject
    lateinit var daggerPresenter: Lazy<ConfigureContactActivityPresenter>

    @InjectPresenter
    lateinit var presenter: ConfigureContactActivityPresenter

    @ProvidePresenter
    fun providePresenter(): ConfigureContactActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.activity_contact
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.fetchContactInfo()
        pick.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, SELECT_PHONE_NUMBER)
        }
        next.setOnClickListener {
            presenter.saveData(
                info.editText?.text.toString(),
                name.editText?.text.toString(),
                address.editText?.text.toString()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null)
            return
          if (requestCode == SELECT_PHONE_NUMBER)
         parseContactInfo(data)
    }

    private fun parseContactInfo(data: Intent) {
        val cursor: Cursor? = requireActivity().contentResolver.query(
            data.data!!,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME),
            null, null, null)

        // If the cursor returned is valid, get the phone number
        cursor?.let {
            if (cursor.moveToFirst()) {
                val contactNumber: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contactName: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME))
                info.editText?.setText(contactNumber)
                name.editText?.setText(contactName)
            }
        }


        cursor?.close()
    }

    override fun setData(contactInfo: String?, contactName: String?) {
        info.editText?.setText(contactInfo)
        name.editText?.setText(contactName)
    }

    override fun showContactInfoEmptyError() {
        Toast.makeText(requireContext(), R.string.configure_contact_validation_error, Toast.LENGTH_SHORT).show()
    }

    override fun nextScreen() {
        navController.navigate(R.id.action_configureContactFragment_to_configureMessageFragment)
    }
}
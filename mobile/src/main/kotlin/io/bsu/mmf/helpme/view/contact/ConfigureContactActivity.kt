package io.bsu.mmf.helpme.view.contact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.contact.ContactPresenter
import kotlinx.android.synthetic.main.activity_contact.*
import javax.inject.Inject


//class ConfigureContactActivity : AppCompatActivity(), ConfigureContactView {
//    @Inject
//    lateinit var presenter: ContactPresenter
//
//    private val SELECT_PHONE_NUMBER: Int = 1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contact)
//        presenter.bind(this)
//        presenter.fetchContactInfo()
//        pick.setOnClickListener {
//            val i = Intent(Intent.ACTION_PICK)
//            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
//            startActivityForResult(i, SELECT_PHONE_NUMBER)
//        }
//        next.setOnClickListener {
//            presenter.saveData(info.editText?.text.toString(), name.editText?.text.toString())
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode != Activity.RESULT_OK || data == null)
//            return
//      //  if (requestCode == SELECT_PHONE_NUMBER)
//           // parseContactInfo(data)
//    }
//
////    private fun parseContactInfo(data: Intent) {
////        val cursor: Cursor? = contentResolver.query(
////                data.data!!,
////                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME),
////                null, null, null)
////
////        // If the cursor returned is valid, get the phone number
////        if (cursor.moveToFirst()) {
////            val contactNumber: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
////            val contactName: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME))
////            info.editText?.setText(contactNumber)
////            name.editText?.setText(contactName)
////        }
////
////        cursor.close()
////    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.unbind()
//    }
//
//    override fun setData(contactInfo: String?, contactName: String?) {
//        info.editText?.setText(contactInfo)
//        name.editText?.setText(contactName)
//    }
//
//    override fun showContactInfoEmptyError() {
//        Toast.makeText(this, R.string.configure_contact_validation_error, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun nextScreen() {
//        val i = Intent(this, ConfigureMessageActivity::class.java)
//        startActivity(i)
//    }
//}
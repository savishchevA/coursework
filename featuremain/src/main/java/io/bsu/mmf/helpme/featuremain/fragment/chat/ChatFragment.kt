package io.bsu.mmf.helpme.featuremain.fragment.chat

import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : BaseFragment(R.layout.fragment_chat) {

}


object Constant {

    const val PREF_USERNAME = "user"
    const val PREF_USER_ID = "id"
    const val PREF_EMAIL = "email"

    val time: String
        get() = SimpleDateFormat("dd MMM yyyy , HH.mm", Locale.getDefault()).format(Date())
}

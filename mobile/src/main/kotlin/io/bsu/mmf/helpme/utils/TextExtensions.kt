package io.bsu.mmf.helpme.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout


val TextInputLayout.text: String
    get() = editText!!.text.toString()

fun TextInputLayout.setText(text: String) = editText!!.setText(text)

fun TextInputLayout.setText(@StringRes text: Int) = editText!!.setText(text)

fun TextInputLayout.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}
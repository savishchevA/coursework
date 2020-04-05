package io.bsu.mmf.helpme.featuremain.adapter.holder

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.*
import com.airbnb.epoxy.*
import com.google.android.material.button.MaterialButton
import io.bsu.mmf.helpme.featuremain.R


@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddContactModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {



    private val btn: MaterialButton

    init {
        inflate(context, R.layout.list_item_add_contact, this)

        btn = findViewById(R.id.btn_add_new)

    }


    @set:CallbackProp
    var addContactListener: (() -> Unit)? = null


    @AfterPropsSet
    fun bind() {

        btn.setOnClickListener {
            addContactListener?.invoke()
        }

    }
}
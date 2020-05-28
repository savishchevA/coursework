package io.bsu.mmf.helpme.featuremain.adapter.holder.chat

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import io.bsu.mmf.helpme.featuremain.R


@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class OtherMessageModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private val messageTextView: TextView

    init {
        inflate(context, R.layout.list_item_other_message, this)

        messageTextView = findViewById(R.id.tvOtherMessage)

    }

    @set:ModelProp
    var message: String = ""


    @AfterPropsSet
    fun bind() {
        messageTextView.text = message
    }
}
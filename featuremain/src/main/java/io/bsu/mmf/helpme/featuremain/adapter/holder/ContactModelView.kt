package io.bsu.mmf.helpme.featuremain.adapter.holder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.*
import com.airbnb.epoxy.*
import io.bsu.mmf.helpme.featuremain.R


@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ContactModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val contactA: ImageView
    private val contactN: TextView
    private val contactP: TextView
    private val contactAd: TextView


    init {
        inflate(context, R.layout.list_item_contact, this)

        contactN = findViewById(R.id.contactName)
        contactP = findViewById(R.id.contactPhone)
        contactAd = findViewById(R.id.contactAddress)
        contactA = findViewById(R.id.contactAvatar)

    }

    @set:CallbackProp
    var onContactClickListener: (() -> Unit)? = null

    @set:ModelProp(ModelProp.Option.DoNotHash)
    var img: ByteArray? = null

    @set:ModelProp
    var name: String = ""

    @set:ModelProp
    var phone: String = ""

    @set:ModelProp
    var address: String = ""

    @set:ModelProp
    var modelId: Int = 0


    @AfterPropsSet
    fun bind() {
        if (img != null) {
            contactA.setImageBitmap(BitmapFactory.decodeByteArray(img,0, img!!.size))
        } else {
            contactA.setImageResource(R.drawable.ic_robot)
        }

        contactN.text = name
        contactP.text = phone
        contactAd.text = address

        setOnClickListener {
            onContactClickListener?.invoke()
        }

    }
}
package io.bsu.mmf.helpme.featuremain.fragment.settings.profile

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.*
import com.airbnb.epoxy.*
import com.google.android.material.imageview.ShapeableImageView
import io.bsu.mmf.helpme.featuremain.R
import timber.log.Timber


@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ProfileContactMovelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val contactImage: ShapeableImageView


    init {
        inflate(context, R.layout.view_holder_profile_contact, this)

        contactImage = findViewById(R.id.contactAvatar)

    }

    @set:CallbackProp
    var onContactClickListener: (() -> Unit)? = null

    @set:ModelProp(ModelProp.Option.DoNotHash)
    var img: ByteArray? = null


    @AfterPropsSet
    fun bind() {
        Timber.e("Current image: ${img}")
        if (img != null) {
            contactImage.setImageBitmap(BitmapFactory.decodeByteArray(img,0, img!!.size))
        } else {
            contactImage.setImageResource(R.drawable.ic_robot)
        }

        setOnClickListener {
            onContactClickListener?.invoke()
        }

    }
}
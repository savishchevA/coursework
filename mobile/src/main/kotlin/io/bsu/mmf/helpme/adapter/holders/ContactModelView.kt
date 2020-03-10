package io.bsu.mmf.helpme.adapter.holders

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.airbnb.epoxy.*
import com.google.android.material.card.MaterialCardView
import io.bsu.mmf.helpme.R

@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ContactModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private val name: TextView
    private val phone: TextView
    private val address: TextView


    init {
        inflate(context, R.layout.list_item_contact, this)
        name = findViewById(R.id.full_name)
        phone = findViewById(R.id.phone_number)
        address = findViewById(R.id.address)

        radius = context.resources.getDimension(R.dimen.card_corner_radius)
    }


    @set:ModelProp
    var fullName: String = ""

    @set:ModelProp
    var phoneNumber: String = ""

    @set:ModelProp
    var contactAddress: String = ""


    @AfterPropsSet
    fun bind() {
        name.text = fullName
        phone.text = phoneNumber
        address.text = contactAddress

    }
}
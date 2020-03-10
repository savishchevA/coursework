package io.bsu.mmf.helpme.adapter.holders

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.card.MaterialCardView
import io.bsu.mmf.helpme.R


@ModelView(saveViewState = false, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TrainModelView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private val name: TextView
    private val day: TextView
    private val distance: TextView


    init {
        inflate(context, R.layout.list_item_train, this)
        name = findViewById(R.id.trainName)
        day = findViewById(R.id.trainDay)
        distance = findViewById(R.id.trainDistance)


//        radius = context.resources.getDimension(R.dimen.card_corner_radius)
//        elevation = context.resources.getDimension(R.dimen.card_elevation)
    }


    @set:ModelProp
    var trainName: String = ""

    @set:ModelProp
    var trainDistance: String = ""

    @set:ModelProp
    var trainDate: String = ""


    @AfterPropsSet
    fun bind() {
        name.text = trainName
        day.text = trainDate
        distance.text = trainDistance

    }
}
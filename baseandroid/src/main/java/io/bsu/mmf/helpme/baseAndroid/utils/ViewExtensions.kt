package io.bsu.mmf.helpme.baseAndroid.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.shape.*
import io.bsu.mmf.helpme.baseAndroid.R

fun View.setTopRoundedBackground() {
    val shapePathModel = ShapeAppearanceModel.builder()
        .setTopLeftCorner(RoundedCornerTreatment())
        .setTopRightCorner(RoundedCornerTreatment())
        .setTopLeftCornerSize(100f)
        .setTopRightCornerSize(100f)
        .build()


    val roundedMaterialShape = MaterialShapeDrawable(shapePathModel)
    roundedMaterialShape.fillColor =
        ContextCompat.getColorStateList(this.context, R.color.white)
    this.background = roundedMaterialShape
}
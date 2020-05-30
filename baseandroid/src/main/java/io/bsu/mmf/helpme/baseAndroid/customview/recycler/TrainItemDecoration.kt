package io.bsu.mmf.helpme.baseAndroid.customview.recycler

import android.content.Context
import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.bsu.mmf.helpme.baseAndroid.R
import io.bsu.mmf.helpme.data.train.TrainItem

class TrainItemDecoration(
    private val context: Context,
    list: List<TrainItem>
) : RecyclerView.ItemDecoration() {

    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint: Paint
    private val datePaint: Paint
    private var dateList: List<TrainItem> = list
    private var radius: Int = 0
    private var curPosition = 0

    init {
        circlePaint.apply {
            color = ContextCompat.getColor(context, R.color.colorPrimary)
            style = Paint.Style.FILL
            strokeWidth = dp2Px(2)
        }

        radius = dp2Px(8).toInt()

        linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.apply {
            color = ContextCompat.getColor(context, R.color.colorPrimaryVariant)
            strokeWidth = dp2Px(2)
        }

        datePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        datePaint.apply {
            color = ContextCompat.getColor(context, R.color.colorPrimary)
            textSize = dp2Px(16)
        }
    }

    private fun dp2Px(value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            top = dp2Px(20).toInt()
            left = dp2Px(80).toInt()
        }

    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        if (dateList.size >= 2) {


            val childCount = parent.childCount
            val layoutManager = parent.layoutManager!!
            for (i in 0 until childCount step 1) {
                val childView = parent.getChildAt(i)
                val leftDecorationWith = layoutManager.getLeftDecorationWidth(childView)
                val topDecorationHeight = layoutManager.getTopDecorationHeight(childView)

                val childLayoutPosition = parent.getChildLayoutPosition(childView)

                val startX = leftDecorationWith
                val topStartY = childView.top - topDecorationHeight
                val topStopY = childView.top + childView.height / 2 - radius

                val bottomStartY = childView.top + childView.height / 2 + radius
                val bottomStopY = childView.bottom

                if (childLayoutPosition > curPosition) {

                    linePaint.color = ContextCompat.getColor(context, R.color.bg)
                    circlePaint.color = ContextCompat.getColor(context, R.color.bg)
                    circlePaint.style = Paint.Style.STROKE
                } else {
                    linePaint.color = ContextCompat.getColor(context, R.color.bg)
                    circlePaint.color = ContextCompat.getColor(context, R.color.bg)
                    circlePaint.style = Paint.Style.FILL
                }

                if (childLayoutPosition == curPosition) {
                    circlePaint.style = Paint.Style.STROKE
                    circlePaint.color = ContextCompat.getColor(context, R.color.futureTrainingColor)
                    canvas.drawCircle(
                        leftDecorationWith.toFloat(),
                        childView.top + childView.height / 2f,
                        dp2Px(2),
                        circlePaint
                    )
                }


                canvas.drawText(
                    dateList[i].date,
                    40f,
                    childView.top + childView.height / 2f + dp2Px(6),
                    datePaint
                )

                canvas.drawCircle(
                    leftDecorationWith.toFloat(),
                    childView.top + childView.height / 2f,
                    radius.toFloat(),
                    circlePaint
                )

                when (childLayoutPosition) {
                    0 -> {
                        if (childLayoutPosition == curPosition) {
                            linePaint.color =
                                ContextCompat.getColor(context, R.color.bg)
                        }

                        canvas.drawLine(
                            startX.toFloat(),
                            bottomStartY.toFloat(),
                            startX.toFloat(),
                            bottomStopY.toFloat(),
                            linePaint
                        )
                    }
                    parent.adapter!!.itemCount - 1 -> {
                        canvas.drawLine(
                            startX.toFloat(),
                            topStartY.toFloat(),
                            startX.toFloat(),
                            topStopY.toFloat(),
                            linePaint
                        )
                    }
                    else -> {
                        canvas.drawLine(
                            startX.toFloat(),
                            topStartY.toFloat(),
                            startX.toFloat(),
                            topStopY.toFloat(),
                            linePaint
                        )

                        if (childLayoutPosition == curPosition) {
                            linePaint.color =
                                ContextCompat.getColor(context, R.color.bg)
                        }

                        canvas.drawLine(
                            startX.toFloat(),
                            bottomStartY.toFloat(),
                            startX.toFloat(),
                            bottomStopY.toFloat(),
                            linePaint
                        )

                    }
                }


            }
        }
    }
}
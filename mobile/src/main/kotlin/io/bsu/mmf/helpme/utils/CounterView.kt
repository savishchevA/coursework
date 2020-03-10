package io.bsu.mmf.helpme.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import io.bsu.mmf.helpme.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.concurrent.TimeUnit


class CounterView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val animatorSet = AnimatorSet()


    private var initCount: Int
    private var countColor: Int
    private var radiusColor: Int
    private val mPulseDrawableList = mutableListOf<TimeDrawable>()
    private val mDummyAnimTarget = DummyAnimTarget



    init {

        val attrsValues = context.obtainStyledAttributes(attrs, R.styleable.CounterView)
        initCount = attrsValues.getInteger(R.styleable.CounterView_init_count, 1)
        countColor = attrsValues.getInteger(R.styleable.CounterView_count_color, 1)
        radiusColor = attrsValues.getResourceId(R.styleable.CounterView_radius_color, 0)


        attrsValues.recycle()

    }

    private var mRadius = 0f
    private var mCenterX = 0f
    private var mCenterY = 0f



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width =
                MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        val height =
                MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        mCenterX = x + width * 0.5f
        mCenterY = y + height * 0.5f
        mRadius = Math.min(width, height) * 0.25f
        // mRadius = sqrt((mCenterX.pow(2) + mCenterY.pow(2)).toDouble()).toFloat() / 2
        build(true)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private val radiusAlpha = 60
    var ar: Int = Color.argb(radiusAlpha, 255, 0, 0)
    var ag: Int = Color.argb(radiusAlpha, 0, 255, 0)
    var ab: Int = Color.argb(radiusAlpha, 0, 0, 255)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = radiusColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.alpha = 80

   //     canvas.drawCircle(mCenterX, mCenterY, mRadius, paint)

      //  drawText(canvas)
        // drawBitmap(canvas)
    }

//    fun drawText(canvas: Canvas) {
//
//        repeat(initCount) {
//            postInvalidate()
//            postInvalidateDelayed(TimeUnit.SECONDS.toMillis(1));
//            paint.color = countColor
//            paint.style = Paint.Style.FILL
//            paint.textSize = 26f
//            canvas.drawText((it + 1).toString(), mCenterX, mCenterY, paint)
//        }
//
//
//    }


    fun clear() {
        animatorSet.cancel()
        animatorSet.childAnimations.clear()
        //mPulseDrawableList.clear()
        postInvalidate()
    }

    private fun build(isNeedClear: Boolean) {

        clear()

        val animatorList = mutableListOf<Animator>()
        for (i in 0 until 10) {
            val pulseDrawable = TimeDrawable(
                    countColor,
                    (i+1).toString(),
                    mCenterX,
                    mCenterY
            )

            mPulseDrawableList.add(pulseDrawable)


            val animator = ObjectAnimator.ofFloat(
                    mDummyAnimTarget, "animValue", 0f, 1f
            ).apply {


                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                startDelay = 1000.toLong()

                addUpdateListener {
                    val animValue = it.animatedValue as? Float ?: 0F
                    pulseDrawable.update()
                }
            }
            animatorList.add(animator)
        }

        animatorSet.playTogether(animatorList)
        animatorSet.start()
    }




}
object DummyAnimTarget
class TimeDrawable(
        color: Int,
        private val text: String,
        private val mCenterX: Float,
        private val mCenterY: Float
) : Drawable() {

    private var mPaint: Paint = Paint()

    init {
        mPaint.apply {
            this.color = color
            style = Paint.Style.FILL
            textSize = 32f
        }
    }

    fun update() {
        invalidateSelf()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText(text, mCenterX, mCenterY, mPaint)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun setAlpha(alpha: Int) {}
    override fun getOpacity(): Int = PixelFormat.TRANSPARENT
}
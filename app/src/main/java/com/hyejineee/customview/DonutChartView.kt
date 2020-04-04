package com.hyejineee.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View

class DonutChartView : View {

    private var categoryValueMap: MutableMap<String, Int> = mutableMapOf()
    private var valuesTotalSum = 0

    private var viewHeight = 0
    private var viewWidth = 0
    var strokeWidth = 0
        set(value) {
            field = value
            invalidate()
        }

    private lateinit var dm: DisplayMetrics

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        dm = resources.displayMetrics
        viewWidth = MeasureSpec.getMode(widthMeasureSpec).let {
            val w = MeasureSpec.getSize(widthMeasureSpec)
            when (it) {
                MeasureSpec.AT_MOST -> (50 * dm.density).toInt()
                MeasureSpec.EXACTLY -> w
                else -> 0
            }
        }
        viewHeight = MeasureSpec.getMode(heightMeasureSpec).let {
            val h = MeasureSpec.getSize(heightMeasureSpec)
            when (it) {
                MeasureSpec.AT_MOST -> (50 * dm.density).toInt()
                MeasureSpec.EXACTLY -> h
                else -> 0
            }
        }
        setMeasuredDimension(viewWidth, viewHeight)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val strokeW = strokeWidth * dm.density

        val backgroundP = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.GRAY
            strokeWidth = strokeW
        }

        val centerPoint = Pair(viewWidth.toFloat() / 2, viewHeight.toFloat() / 2)
        val radius = viewWidth.toFloat() / 2 - strokeW

        canvas?.drawCircle(
            centerPoint.first,
            centerPoint.second,
            radius,
            backgroundP
        )

        val box = RectF(
            centerPoint.first - radius,
            centerPoint.second - radius,
            centerPoint.first + radius,
            centerPoint.second + radius
        )

        var startAngle = -90f
        for ((c, v) in categoryValueMap) {
            val p = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = strokeW
                color = when {
                    c.contains("1") -> Color.MAGENTA
                    c.contains("2") -> Color.CYAN
                    c.contains("3") -> Color.YELLOW
                    else -> Color.BLACK
                }
                strokeCap = Paint.Cap.ROUND
            }

            var endAngle = (v / valuesTotalSum.toFloat()) * 360.0f
            Log.d("donut", "endAngle : $endAngle")

            canvas?.drawArc(box, startAngle, endAngle, false, p)
            startAngle += endAngle
        }

    }

    fun setValue(category: String, v: Int) {
        categoryValueMap[category] = v
        valuesTotalSum = categoryValueMap.values.sum()

        Log.d("donut", "add : $valuesTotalSum")

        invalidate()
    }
}
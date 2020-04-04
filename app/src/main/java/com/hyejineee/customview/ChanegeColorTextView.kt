package com.hyejineee.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ChangeColorTextView : AppCompatTextView {

    var dustDimension = 0
    set(value) {
        field = value
        setLevelColor()
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    private fun setLevelColor(){
        when{
            dustDimension > 150 -> this.setTextColor(Color.RED)
            dustDimension in 31..150 -> this.setTextColor(Color.GREEN)
            else -> this.setTextColor(Color.BLUE)
        }
    }
}
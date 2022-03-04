package com.example.rickandmortytest.utils

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.rickandmortytest.R

class ShadowImageView : SquareImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        foreground = ContextCompat.getDrawable(context, R.drawable.shadow_background)
    }
}
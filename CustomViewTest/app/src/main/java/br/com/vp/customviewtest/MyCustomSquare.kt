package br.com.vp.customviewtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * @author diegovidal on 19/09/2018.
 */
class MyCustomSquare(context: Context, attributeSet: AttributeSet? = null)
    : View(context, attributeSet) {

    private var paint = Paint(Color.RED)
    private var rect = Rect(10, 10, 80, 80)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(rect, paint)
    }


}
package com.dvidal.constraintlayouttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.constraintLayout
        val lpCl = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT )
        constraintLayout.layoutParams = lpCl

        // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

        val imageView = ImageView(this)
        imageView.id = R.id.imageView
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.singapore)

        val imageLp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
            dpToPx(200) )

        imageLp.topToTop = constraintLayout.id
        imageLp.leftToLeft = constraintLayout.id
        imageLp.rightToRight = constraintLayout.id
        imageView.layoutParams = imageLp

        constraintLayout.addView(imageView)

        // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

        val textView = TextView(this)
        textView.text = getString(R.string.simple_text)
        textView.setPadding(dpToPx(16), dpToPx(8), 0, 0)

        val textLp = ConstraintLayout.LayoutParams( dpToPx(180) ,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)

        textLp.topToBottom = imageView.id
        textLp.leftToLeft = constraintLayout.id
        textView.layoutParams = textLp

        constraintLayout.addView(textView)

        // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

        setContentView(constraintLayout)
    }

    private fun dpToPx(dp: Int): Int{

        val displayMetrics = resources.displayMetrics
        return Math.round(dp * displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)
    }
}

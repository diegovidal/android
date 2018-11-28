package com.dvidal.objectanimatortest

import android.animation.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun xmlEffect(v: View) {

        val anim  = AnimatorInflater.loadAnimator(this, R.animator.animator_fade) as? ObjectAnimator
        anim?.target = image

        if (flag) anim?.start() else anim?.reverse()
        flag = !flag
    }

    fun apiEffect(v: View) {

        val anim  = ObjectAnimator.ofFloat(image, "alpha", 1f, 0f)
        anim.duration = 2000
        anim.repeatCount = 2

        anim.addListener( object : AnimatorListenerAdapter(){

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                Log.i("Script", "onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                Log.i("Script", "onAnimationEnd")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                super.onAnimationRepeat(animation)
                Log.i("Script", "onAnimationRepeat")
            }
        } )

        if (flag) anim.start() else anim.reverse()
        flag = !flag
    }

    fun multiplesEffect(v: View) {

        val anim1  = PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
        val anim2  = PropertyValuesHolder.ofFloat("x", 0f, 30f)
        val anim3  = PropertyValuesHolder.ofFloat("y", 0f, 30f)

        val objAnim = ObjectAnimator.ofPropertyValuesHolder(image, anim1, anim2, anim3)
        objAnim.duration = 6000

        if (flag) objAnim.start() else objAnim.reverse()
        flag = !flag
    }
}

package com.dvidal.relativelayouttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val rl = RelativeLayout(this)
        val lp = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT)

        val paddingStand = resources.getDimension(R.dimen.padding_stand).toInt()
        rl.setPadding(paddingStand, paddingStand, paddingStand, paddingStand)
        rl.layoutParams = lp

        val tvUser = TextView(this)
        tvUser.text = "User: "
        tvUser.id = R.id.tv_user
        val tvUserLp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        tvUser.layoutParams = tvUserLp
        rl.addView(tvUser)

        val etUser = EditText(this)
        val etUserLp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        etUserLp.addRule(RelativeLayout.RIGHT_OF, tvUser.id)
        etUser.layoutParams = etUserLp
        rl.addView(etUser)

        setContentView(rl)
    }
}

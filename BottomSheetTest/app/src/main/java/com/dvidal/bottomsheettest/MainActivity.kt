package com.dvidal.bottomsheettest

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import com.dvidal.bottomsheettest.dialog.CustomBottomSheetDialog
import com.dvidal.bottomsheettest.dialog.CustomBottomSheetDialogFragment
import com.dvidal.bottomsheettest.dialog.CustomBottomSheetDialogFragment.Companion.FAKE_COLLECTION_KEY
import com.dvidal.bottomsheettest.dialog.CustomBottomSheetDialogFragment.Companion.FRAGMENT_KEY
import com.dvidal.bottomsheettest.domain.ActionAdapter
import com.dvidal.bottomsheettest.util.FakeCollection

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*

class MainActivity : AppCompatActivity() {

    private var offsetY = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeFabListener()
        initializeBottomSheet()
        initializeListView()
    }

    private fun initializeListView() {

        ActionAdapter(this, FakeCollection.getActions()){ position ->

            Toast.makeText(this, "Position is $position", Toast.LENGTH_SHORT).show()
        }.also {

            lv_actions.adapter = it
        }
    }

    private fun initializeFabListener() {

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initializeBottomSheet() {

        BottomSheetBehavior.from(rv_bottom_sheet).also {

            it.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                    if (offsetY < slideOffset) {
                        fab.hide()
                    } else if (offsetY > slideOffset) {
                        fab.show()
                    }

                    offsetY = slideOffset
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {

                }
            })
        }
    }

    fun callBottomSheetDialog(v: View) {

        CustomBottomSheetDialog(this).also {
        }.show()
    }

    fun callBottomSheetDialogFragment(v: View) {

        CustomBottomSheetDialogFragment().also {

            it.arguments = Bundle().also { bundle ->
                bundle.putParcelableArrayList(FAKE_COLLECTION_KEY, FakeCollection.getItems())
            }

        }.show(supportFragmentManager, FRAGMENT_KEY)
    }
}



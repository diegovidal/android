package com.dvidal.bottomsheettest.dialog

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.widget.Toast
import com.dvidal.bottomsheettest.R
import com.dvidal.bottomsheettest.domain.ActionAdapter
import com.dvidal.bottomsheettest.domain.ItemAdapter
import com.dvidal.bottomsheettest.util.FakeCollection
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

/**
 * @author diegovidal on 21/11/18.
 */
class CustomBottomSheetDialog(context: Context): BottomSheetDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_sheet_dialog)

        initializeGridView()
    }

    private fun initializeGridView() {

        ItemAdapter(context, FakeCollection.getItems()){ position ->
            Toast.makeText(context, "Position is $position", Toast.LENGTH_SHORT).show()
        }.also {

            gv_items.numColumns = 3
            gv_items.adapter = it
        }
    }
}
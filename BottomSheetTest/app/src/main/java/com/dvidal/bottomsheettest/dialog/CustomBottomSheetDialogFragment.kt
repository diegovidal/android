package com.dvidal.bottomsheettest.dialog

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dvidal.bottomsheettest.R
import com.dvidal.bottomsheettest.domain.Item
import com.dvidal.bottomsheettest.domain.ItemAdapter
import com.dvidal.bottomsheettest.util.FakeCollection
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

/**
 * @author diegovidal on 21/11/18.
 */
class CustomBottomSheetDialogFragment: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottom_sheet_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeGridView()
    }

    private fun initializeGridView() {

        val fakeCollection = arguments?.getParcelableArrayList<Item>(FAKE_COLLECTION_KEY) as ArrayList<Item>

        ItemAdapter(requireContext(), fakeCollection){ position ->
            Toast.makeText(requireContext(), "Position is $position", Toast.LENGTH_SHORT).show()
        }.also {

            gv_items.numColumns = 3
            gv_items.adapter = it
        }
    }

    companion object {

        const val FRAGMENT_KEY = "FRAGMENT_KEY"
        const val FAKE_COLLECTION_KEY = "FAKE_COLLECTION_KEY"
    }
}
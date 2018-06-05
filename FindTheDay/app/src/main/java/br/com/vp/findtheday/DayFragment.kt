package br.com.vp.findtheday

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_day.view.*
import javax.inject.Inject

/**
 * @author diegovidal on 05/06/2018.
 */

class DayFragment : DialogFragment() {

    @Inject
    lateinit var dayChooser: DayChooser

    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    private val mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var enteredNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.getComponent().inject(this)

        arguments?.let{
            enteredNumber = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        val day = dayChooser.getTheDay(enteredNumber)

        view.tvValue?.text = day

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener?.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context?.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {
        
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(index: Int): DayFragment {

            val fragment = DayFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, index)
            fragment.arguments = args
            return fragment
        }
    }
}

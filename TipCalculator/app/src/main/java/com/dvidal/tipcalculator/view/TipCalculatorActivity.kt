package com.dvidal.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.dvidal.tipcalculator.R
import com.dvidal.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.dvidal.tipcalculator.viewmodel.TipCalculatorViewModel

import kotlinx.android.synthetic.main.activity_tip_calculator.*

class TipCalculatorActivity : AppCompatActivity(),
        SaveDialogFragment.Callback, LoadDialogFragment.Callback {

    lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        binding.vm = ViewModelProviders.of(this)
                .get(TipCalculatorViewModel::class.java)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            R.id.action_load -> {
                showLoadDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }

    private fun showLoadDialog() {
        val loadFragment = LoadDialogFragment()
        loadFragment.show(supportFragmentManager, "SaveDialog")
    }

    override fun onSaveTip(name: String) {

        binding.vm?.saveCurrentTip(name)
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onTipSelected(name: String) {

        binding.vm?.loadTipCalculation(name)
        Snackbar.make(binding.root, "Loaded $name", Snackbar.LENGTH_SHORT).show()
    }
}

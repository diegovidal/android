package com.dvidal.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.dvidal.tipcalculator.R
import com.dvidal.tipcalculator.model.RestaurantCalculator
import com.dvidal.tipcalculator.model.TipCalculation

/**
 * @author diegovidal on 29/09/2018.
 */
class TipCalculatorViewModel @JvmOverloads
    constructor(app: Application,
                private val calculator: RestaurantCalculator = RestaurantCalculator())
        : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {

        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {

        val tipToSave = lastTipCalculated.copy(locationName = name)

        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }

    fun loadSavedTipCalculationSummaries() : LiveData<List<TipCalculationSummaryItem>> {

        return Transformations.map(calculator.loadSavedTipCalculations()) { tipCalculationObjects ->
            tipCalculationObjects.map {
                TipCalculationSummaryItem(it.locationName,
                        getApplication<Application>().getString(R.string.dollar_amount, it.grandTotal))
            }
        }
    }

    fun loadTipCalculation(name: String) {

        val tc = calculator.loadTipCalculationByLocationName(name)

        if (tc != null) {
            inputCheckAmount = tc.checkAmount.toString()
            inputTipPercentage = tc.tipPct.toString()

            updateOutputs(tc)
            notifyChange()
        }
    }

    fun calculateTip() {

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null){
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

    companion object {

        private const val TAG = "TipCalculatorViewModel"
    }
}
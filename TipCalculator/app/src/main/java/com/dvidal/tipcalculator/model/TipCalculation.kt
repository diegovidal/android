package com.dvidal.tipcalculator.model

/**
 * @author diegovidal on 29/09/2018.
 */
data class TipCalculation(
        val locationName: String = "",
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
)
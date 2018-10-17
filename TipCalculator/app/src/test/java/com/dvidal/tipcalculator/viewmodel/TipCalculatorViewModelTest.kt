package com.dvidal.tipcalculator.viewmodel

import android.app.Application
import com.dvidal.tipcalculator.R
import com.dvidal.tipcalculator.model.RestaurantCalculator
import com.dvidal.tipcalculator.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 29/09/2018.
 */
class TipCalculatorViewModelTest {

    private lateinit var calculatorViewModel: TipCalculatorViewModel

    @Mock
    lateinit var mockCalculator: RestaurantCalculator

    @Mock
    lateinit var application: Application

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        stupResource(0.0, "$0.00")
        calculatorViewModel = TipCalculatorViewModel(application, mockCalculator)
    }

    private fun stupResource(given: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"


        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)

        stupResource(10.0, "$10.00")
        stupResource(1.5, "$1.50")
        stupResource(11.5, "$11.50")

        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)
    }

    @Test
    fun testCalculateTipBadCheckInputAmount() {

        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }

    @Test
    fun testCalculateTipBadTipPercent() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }

    @Test
    fun testSaveCurrentTip() {

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        val stubLocationName = "Green Eggs and Bacon"

        fun setupTipCalculation() {
            calculatorViewModel.inputCheckAmount = "10.00"
            calculatorViewModel.inputTipPercentage = "15"


            `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        }

        setupTipCalculation()
        calculatorViewModel.calculateTip()
        calculatorViewModel.saveCurrentTip(stubLocationName)

        verify(mockCalculator).saveTipCalculation(stub.copy(locationName = stubLocationName))
        assertEquals(stubLocationName, calculatorViewModel.locationName)
    }
}
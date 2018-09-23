package edu.towson.cosc431.Patel.TipCalculator_Patel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class TipCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set button listener
        calculateButton.setOnClickListener {calculate_tip()}

        // set the radio group listener

        //radioGroup.setOnCheckedChangeListener { group, checkedId -> updateLabel(checkedId) }


    }

    /**
     * Change the label based on the selected radio button

    private fun updateLabel(checkedId: Int) {
        val titleText = when(checkedId) {
            radioF2C.id -> R.string.fahrenheit_to_celsius
            radioC2F.id -> R.string.celsius_to_fahrenheit
            else -> throw Exception("Unexpected radio button")
        }
        titleTv.text = resources.getText(titleText)
    }*/

    /**
     * Handler for the convert button click
     */
    fun calculate_tip()
    {
        // get the input Amount from the edit text
        val inputString = checkAmount.editableText.toString()
        if(inputString.isEmpty())
        {
            display.text = "Enter check amount"
        }
        else
        {
            // check the id of the checked radio button
            val checkedId = radioGroup.checkedRadioButtonId

            // set the percentage based on the checked id
            val percentValue: Double? = when(checkedId) {
                radio10.id -> 10.0
                radio20.id -> 20.0
                radio30.id -> 30.0
                else -> throw Exception("Unexpected value!")
            }
            if (percentValue == null){
                display.text= "Select tip percent"
            }
            else{
                // calculation
                val result = calculate(percentValue, inputString)

                // set the result textview
                when(result) {
                    null -> display.text = "Please enter required values"
                    else -> display.text = result
                }
            }
        }
    }

    /**
     * A function to do the calculation of tip and total amount.
     */
    fun calculate(percentValue: Double, strValue: String): String? {
        try {
            val amount = strValue.toDouble()
            val calculated_tip = (percentValue * amount)/100
            val total_amount = amount + calculated_tip

            return "Your calculated tip is $ ${String.format("%.2f", calculated_tip)} and your total is $ ${String.format("%.2f", total_amount)}"
        } catch (e: NumberFormatException) {
            return null
        }
    }




}

package com.example.myapplication

import android.R.id.message
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import org.mariuszgromada.math.mxparser.Expression


class standardCalculator : AppCompatActivity() {


    private lateinit var previousCalculation: TextView
    private lateinit var display: EditText
    private lateinit var display2: EditText
    private lateinit var displayEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.standard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.standard_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayEditText = findViewById(R.id.displayEditText)
        previousCalculation = findViewById(R.id.calculationsView)
        display = findViewById(R.id.displayEditText)
        display2 = findViewById(R.id.calculationsView)

        display.setShowSoftInputOnFocus(false)
    }


    private fun isOperator(char: String): Boolean {
        val operators = listOf("*(-1)",".","+", "-", "*", "/", "(", ")", "sin(", "cos(", "tan(", "ln(", "log(", "sqrt(", "%", "^(2)", "^(")
        return operators.contains(char)
    }

    private fun isOperatorSmallerGroup(char: String): Boolean {
        val operators = listOf(".","+", "*", "/", "-", "%")
        return operators.contains(char)
    }

    private fun updateText(strToAdd: String) {
        val oldText = display.getText().toString();
        val cursorPos = display.selectionStart
        val leftStr = oldText.substring(0, cursorPos)
        val rightStr = oldText.substring(cursorPos)

        if (oldText.isEmpty()) {
            if(strToAdd.equals("-") || (!isOperatorSmallerGroup(strToAdd) && !strToAdd.equals("*(-1)"))){
                display.setText("$leftStr$strToAdd$rightStr")
                display.setSelection(cursorPos + strToAdd.length)
            }else
                Snackbar.make(findViewById(android.R.id.content), "Niepoprawne użycie operatorów", Snackbar.LENGTH_LONG).show()

        } else {
            if (!isOperator(strToAdd)) {
                display.setText("$leftStr$strToAdd$rightStr")
                display.setSelection(cursorPos + strToAdd.length)
            }else{
                val lastChar = oldText[oldText.length - 1]
                if(!isOperator(lastChar.toString())){
                    display.setText("$leftStr$strToAdd$rightStr")
                    display.setSelection(cursorPos + strToAdd.length)
                }else{
                    if(!isOperatorSmallerGroup(lastChar.toString())){
                        display.setText("$leftStr$strToAdd$rightStr")
                        display.setSelection(cursorPos + strToAdd.length)
                    }else{
                        if(!isOperatorSmallerGroup(strToAdd) && !strToAdd.equals("*(-1)")){
                            display.setText("$leftStr$strToAdd$rightStr")
                            display.setSelection(cursorPos + strToAdd.length)
                        }
                        else
                            Snackbar.make(findViewById(android.R.id.content), "Niepoprawne użycie operatorów", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun zeroBTNPush(view: View) {
        updateText(getString(R.string.zeroText))
    }

    fun oneBTNPush(view: View) {
        updateText(getString(R.string.oneText))
    }

    fun twoBTNPush(view: View) {
        updateText(getString(R.string.twoText))
    }

    fun threeBTNPush(view: View) {
        updateText(getString(R.string.threeText))
    }

    fun fourBTNPush(view: View) {
        updateText(getString(R.string.fourText))
    }

    fun fiveBTNPush(view: View) {
        updateText(getString(R.string.fiveText))
    }

    fun sixBTNPush(view: View) {
        updateText(getString(R.string.sixText))
    }

    fun sevenBTNPush(view: View) {
        updateText(getString(R.string.sevenText))
    }

    fun eightBTNPush(view: View) {
        updateText(getString(R.string.eightText))
    }

    fun nineBTNPush(view: View) {
        updateText(getString(R.string.nineText))
    }

    fun dotBTNPush(view: View) {
        updateText(getString(R.string.dotText))
    }

    fun addBTNPush(view: View) {
        updateText(getString(R.string.addText))
    }

    fun substractBTNPush(view: View) {
        updateText(getString(R.string.subtractText))
    }

    fun divideBTNPush(view: View) {
        updateText(getString(R.string.divideText))

    }

    fun multiplyBTNPush(view: View) {
        updateText(getString(R.string.multiplyText))
    }

    fun changeSymbolBTNPush(view: View) {
        updateText("*(-1)")
    }


    fun clearBTNPush(view: View) {
        if(display.equals("")){
            display.setText("")
            display2.setText("")
        }
        else
            display.setText("");
    }

    fun clearAllBTNPush(view: View) {
        display.setText("")
        display2.setText("")
    }

    fun equalBTNPush(view: View) {
        val userExp = display.text.toString()

        val exp = Expression(userExp)
        val result = exp.calculate().toString()

        if(result.equals("NaN")){
//            Toast.makeText(this, "Nieprawidłowa formułą!", Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(android.R.id.content), "Wystąpił błąd w składni", Snackbar.LENGTH_LONG).show();

            display.setText("")
        }
        else{
            val formattedResult = String.format("%.3f", result.toDouble())
            display.setText(formattedResult)
            display.setSelection(formattedResult.length)
            display2.setText(formattedResult)
        }
    }
}
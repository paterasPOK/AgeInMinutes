package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var txtDate: TextView? = null
    private var txtMin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn:Button = findViewById(R.id.button)

        txtDate= findViewById(R.id.dateTxt)
        txtMin= findViewById(R.id.minTxt)




        btn.setOnClickListener {

            clickDatePicker()
        }
    }

    private fun calendarPermission() {

    }

    private fun clickDatePicker(){
        val txtDate: TextView = findViewById(R.id.dateTxt)
        val txtMin: TextView = findViewById(R.id.minTxt)
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            txtDate?.text = selectedDate


            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            txtMin?.text = theDate?.let { dayInMinutes(it,sdf).toString() }


            Toast.makeText(this,"$theDate", Toast.LENGTH_LONG).show()
        },
        year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

    private fun dayInMinutes(theDate: Date, sdf: SimpleDateFormat): Long {

        val theDate = theDate
        val sdf = sdf

        val selectedDateInMins = theDate.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMins = currentDate.time / 60000

        return currentDateInMins - selectedDateInMins

    }
}
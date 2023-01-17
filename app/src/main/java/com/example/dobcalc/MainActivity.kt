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

    private var tvSelectedDate :TextView? = null
    private var tvAgeInMinutes :TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDAtePicker : Button = findViewById(R.id.btnDatePicker)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnDAtePicker.setOnClickListener{
           clickDatePicker()
        }
    }
   private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        //DatePickerDialog.OnDateSetListener(view,year,month,dayOfMonth ->)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { _, Selectedyear, Selectedmonth, SelecteddayOfMonth ->
            Toast.makeText(this,
                "Year was a $Selectedyear , Month was ${Selectedmonth + 1} " +
                        "and Day is $SelecteddayOfMonth",Toast.LENGTH_LONG).show()
            val selectedDate = "$SelecteddayOfMonth/${Selectedmonth + 1}/$Selectedyear"
            tvSelectedDate?.text=selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            theDate?.let{
                val selectedDateInTime = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateInMinutes-selectedDateInTime
                    tvAgeInMinutes?.text=differenceInMinutes.toString()
                }
            }
        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}
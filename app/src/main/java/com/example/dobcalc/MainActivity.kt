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
    private var tvSelectedDate:TextView? = null
    private var tvAgeInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }

    }
   private fun clickDatePicker(){
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this,DatePickerDialog.OnDateSetListener{_, SelectedYear, SelectedMonth, selectedDayOfMonth ->
            val selectedDate="$selectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
            tvSelectedDate=findViewById(R.id.tvSelectedDate)
            tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)
            tvSelectedDate?.text=selectedDate
            //Toast.makeText(this,"Year:${year-SelectedYear}}",Toast.LENGTH_LONG).show()
            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinutes = theDate.time / 6000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                Toast.makeText(this,"Year:${sdf.parse(sdf.format(System.currentTimeMillis()))}",Toast.LENGTH_LONG).show()
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 6000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes


//           Toast.makeText(this,"$differenceInMinutes",Toast.LENGTH_LONG).show()
                    tvAgeInMinutes?.text = "$differenceInMinutes"
                }
            }
        },
            year,
            month,
            day
        )

        dpd.show()
    }
}
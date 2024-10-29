package com.example.ageinminute

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ageinminute.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dataBtn.setOnClickListener {
            btnClickListener()
        }
    }

    fun btnClickListener(){
        var myCalendar = Calendar.getInstance();
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth ->
            var selectedData = "${dayOfMonth}/${month+1}/${year}"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val refactoredData = sdf.parse(selectedData)
            binding.dateText.text = selectedData
            val yearAfterBirth = refactoredData.time/60000
            val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentTimeInMinutes = currentTime.time/60000

            val difference = currentTimeInMinutes - yearAfterBirth
            binding.minuteText.text = difference.toString()
        }, year, month, day).show()


    }
}
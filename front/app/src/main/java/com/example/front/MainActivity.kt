package com.example.front

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import java.util.*

class MainActivity : AppCompatActivity() {
    var isTrain: Boolean = false
    var isAirplane: Boolean = false
    var isOwnCar: Boolean = false
    var maxPrice: Int = 1000 // нужно установить значение из парсинга

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchTrain: Switch = findViewById(R.id.switchTrain)
        val switchAirplane: Switch = findViewById(R.id.switchAirplane)
        val switchOwnCar: Switch = findViewById(R.id.switchOwnCar)

        val seekMinPrice: SeekBar = findViewById(R.id.seekMinPrice)
        val textMinPrice: TextView = findViewById(R.id.textMinPrice)
        val seekMaxPrice: SeekBar = findViewById(R.id.seekMaxPrice)
        val textMaxPrice: TextView = findViewById(R.id.textMaxPrice)

        val buttonDeparture: Button = findViewById(R.id.buttonDeparture)
        val textDateDeparture: TextView = findViewById(R.id.textDateDeparture)
        val buttonArrival: Button = findViewById(R.id.buttonArrival)
        val textDateArrival: TextView = findViewById(R.id.textDateArrival)

        // для календарика, который вызывается при нажатии кнопки
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var departureDate: String
        var arrivalDate: String

        val editDays: EditText = findViewById(R.id.editDays)
        val editHours: EditText = findViewById(R.id.editHours)

        val mainButton: Button = findViewById(R.id.mainButton)

        // обработка нажатий на switch
        switchTrain.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) isTrain = true
            else isTrain = false
        }
        switchAirplane.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) isAirplane = true
            else isAirplane = false
        }
        switchOwnCar.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) isOwnCar = true
            else isOwnCar = false
        }


        seekMinPrice.max = maxPrice // устанавливаем макс. цену
        seekMinPrice.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textMinPrice.setText("${progress.toString()} рублей") // передаём textview значение прогресса
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekMaxPrice.max = maxPrice
        seekMaxPrice.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textMaxPrice.setText("${progress.toString()} рублей")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        buttonDeparture.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // отображает дату в textview под кнопкой
                departureDate = "" + dayOfMonth + "." + month + "." + year
                textDateDeparture.setText(departureDate)
            }, year, month, day)

            dpd.show()
        }

        buttonArrival.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // отображает дату в textview под кнопкой
                arrivalDate = "" + dayOfMonth + "." + month + "." + year
                textDateArrival.setText(arrivalDate)
            }, year, month, day)

            dpd.show()
        }

        var days = editDays.getText().toString()

        mainButton.setOnClickListener{
            if (editDays.getText().toString().equals(""))
            {
                println(0)
            }
            else
            {
                println(days)
            }
        }
    }
}
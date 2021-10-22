package com.example.front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Switch
import android.widget.TextView

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
        val seekPrice: SeekBar = findViewById(R.id.seekPrice)
        val textPrice: TextView = findViewById(R.id.textPrice)

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

        seekPrice.max = maxPrice // устанавливаем макс. цену
        seekPrice.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textPrice.setText(progress.toString()) // дередаём textview значение прогресса
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}
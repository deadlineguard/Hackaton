package com.example.front

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class MainActivity : AppCompatActivity() {
    var isTrain: Boolean = false
    var isAirplane: Boolean = false
    var isOwnCar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchTrain: Switch = findViewById(R.id.switchTrain)
        val switchAirplane: Switch = findViewById(R.id.switchAirplane)
        val switchOwnCar: Switch = findViewById(R.id.switchOwnCar)

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

        val editMin: EditText = findViewById(R.id.editMin)
        val editMax: EditText = findViewById(R.id.editMax)
        val editDays: EditText = findViewById(R.id.editDays)
        val editHours: EditText = findViewById(R.id.editHours)

        val mainButton: Button = findViewById(R.id.mainButton)

        val textViewOutput: TextView = findViewById(R.id.textViewOutput)

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

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.65.75:8080/?vehicle=Автомобиль&startDate=01.11.2021&endDate=31.11.2021&budget=10000&filter=minPrice"
        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    textView.text = "Response: %s".format(response.get("adultTicketPrice"))
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
        )
        queue.add(jsonObjectRequest)

        // вывод данных
        mainButton.setOnClickListener{
            val days = editDays.getText().toString() // оптимальное кол-во дней на поездку
            val hours = editHours.getText().toString() // оптимальное кол-во часов на поездку
            val min = editMin.getText().toString() // минимальная цена поездки
            val max = editMax.getText().toString() // максимальная цена поездки
        }
    }
}

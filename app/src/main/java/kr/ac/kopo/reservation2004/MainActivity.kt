package kr.ac.kopo.reservation2004

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var  chrono : Chronometer
    lateinit var  btnStart : Button
    lateinit var  btnDone : Button
    lateinit var  rg : RadioGroup
    lateinit var  calendar : DatePicker
    lateinit var  timePick : TimePicker
    lateinit var  textResult : TextView
    var selecteddYear :Int = 0
    var selectedMonth :Int = 0
    var selectedDay :Int = 0
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono =findViewById<Chronometer>(R.id.chrono)

        rg =findViewById<RadioGroup>(R.id.rg)
        calendar = findViewById<DatePicker>(R.id.calender)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById(R.id.textResult)


        rg.visibility = View.INVISIBLE
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListener)
        chrono.setOnClickListener {
            chrono.base =SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
            rg.visibility = View.VISIBLE
        }


        textResult.setOnLongClickListener {
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            selecteddYear = calendar.year
            selectedMonth = calendar.month
            selectedDay = calendar.dayOfMonth

            textResult.setText(""+selecteddYear+ "년"+ (selectedMonth +1)+ "월"+ selectedDay + "일")
            textResult.append(""+timePick.currentHour +"시")
            textResult.append(""+timePick.currentMinute + "분")

            rg.visibility = View.INVISIBLE
            calendar.visibility = View.INVISIBLE
            timePick.visibility = View.INVISIBLE

            return@setOnLongClickListener true
        }

//        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            selecteddYear = year
//            selectedMonth = month + 1
//            selectedDay =dayOfMonth
//        }
    }

    var rgListener = OnCheckedChangeListener{group,chekedId->
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId){
            R.id.rgDate ->calendar.visibility=View.VISIBLE
            R.id.rgTime ->timePick.visibility=View.VISIBLE
        }
    }
}
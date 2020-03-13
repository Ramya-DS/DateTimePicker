package com.example.datetimepicker

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), DateTimeDialogListener {

    private lateinit var button: Button
    lateinit var displayTextDate: TextView
    lateinit var displayTextTime: TextView

    private val dateTimeDialog = DateTimeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button = findViewById(R.id.pickButton)
        displayTextDate = findViewById(R.id.displayTextDate)
        displayTextTime=findViewById(R.id.displayTextTime)

        savedInstanceState?.let {
            displayTextDate.text=it.getString("date")
            displayTextTime.text=it.getString("time")
        }

        button.setOnClickListener {

            dateTimeDialog.show(supportFragmentManager, "DATE TIME PICKER")
        }

    }

    override fun closeDialog() {

        supportFragmentManager.let{
            it.beginTransaction().remove(it.findFragmentByTag("DATE TIME PICKER")!!).commit()
        }
    }

    override fun dataTimeChanged(isDate: Boolean, date: String) {
        if(isDate)
            displayTextDate.text=date
        else
            displayTextTime.text=date
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("date",displayTextDate.text.toString())
        outState.putString("time",displayTextTime.text.toString())
    }
}

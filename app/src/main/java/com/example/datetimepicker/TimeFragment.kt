package com.example.datetimepicker


import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter

/**
 * A simple [Fragment] subclass.
 */
class TimeFragment : Fragment() {

    private var mDialogListener: DateTimeDialogListener? = null

    var currentHour = 0
    var currentMinute = 0

    lateinit var timePicker: TimePicker


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_time, container, false)
        timePicker = rootView.findViewById(R.id.time_picker)
        val cancelButton: Button = rootView.findViewById(R.id.cancelButtonTime)
        val clearButton: Button = rootView.findViewById(R.id.clearButtonTime)
        val doneButton: Button = rootView.findViewById(R.id.doneButtonTime)

        val initialHour = timePicker.hour
        val initialMinute = timePicker.minute

        currentHour = initialHour
        currentMinute = initialMinute


        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            currentHour = hourOfDay
            currentMinute = minute
        }

        cancelButton.setOnClickListener {
            mDialogListener!!.closeDialog()
        }

        clearButton.setOnClickListener {
            currentMinute = initialMinute
            currentHour = initialHour
            timePicker.hour = initialHour
            timePicker.minute = initialMinute
        }

        doneButton.setOnClickListener {
            val time: String =
                when {
                    currentHour > 12 -> "${currentHour - 12} : $currentMinute PM"
                    currentHour == 12 -> {
                        "12 : $currentMinute PM"
                    }
                    else -> "$currentHour : $currentMinute AM"
                }
            mDialogListener!!.dataTimeChanged(false, time)
            mDialogListener!!.closeDialog()
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mDialogListener = context as DateTimeDialogListener
        if (mDialogListener == null) {
            throw ClassCastException("$context must implement DateTimeDialogListener")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("hour", currentHour)
        outState.putInt("minute", currentMinute)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            timePicker.hour = it.getInt("hour")
            timePicker.minute = it.getInt("minute")
        }
    }

}

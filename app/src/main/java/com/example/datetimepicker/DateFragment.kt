package com.example.datetimepicker


import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.StyleRes
import kotlinx.android.synthetic.main.fragment_date.view.*
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.logging.SimpleFormatter

/**
 * A simple [Fragment] subclass.
 */
class DateFragment : Fragment() {

    companion object {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
    }

    var mDateTimeDialogListener: DateTimeDialogListener? = null
    lateinit var datePicker: DatePicker

    var currentDate: Int = 0
    var currentMonth: Int = 0
    var currentYear: Int = 0

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_date, container, false)
        datePicker = rootView.findViewById<DatePicker>(R.id.date_picker)
        val cancelButton: Button = rootView.findViewById(R.id.cancelButtonDate)
        val clearButton: Button = rootView.findViewById(R.id.clearButtonDate)
        val doneButton: Button = rootView.findViewById(R.id.doneButtonDate)

        val initialDate: Int = datePicker.dayOfMonth
        val initialMonth: Int = datePicker.month
        val initialYear: Int = datePicker.year

        currentDate = initialDate
        currentMonth = initialMonth
        currentYear = initialYear

        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            currentDate = dayOfMonth
            currentMonth = monthOfYear
            currentYear = year
        }

        cancelButton.setOnClickListener {
            mDateTimeDialogListener!!.closeDialog()
        }

        clearButton.setOnClickListener {
            currentDate = initialDate
            currentMonth = initialMonth
            currentYear = initialYear
            datePicker.updateDate(initialYear, initialMonth, initialDate)
        }

        doneButton.setOnClickListener {
            mDateTimeDialogListener!!.dataTimeChanged(
                true,
                formatter.format(Date(currentYear - 1900, currentMonth, currentDate))
            )
            mDateTimeDialogListener!!.closeDialog()
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mDateTimeDialogListener = context as? DateTimeDialogListener
        if (mDateTimeDialogListener == null ) {
            throw ClassCastException("$context must implement DateTimeDialogListener")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("year", currentYear)
        outState.putInt("month", currentMonth)
        outState.putInt("date", currentDate)
        mDateTimeDialogListener!!.dataTimeChanged(true,formatter.format(Date(currentYear - 1900, currentMonth, currentDate)))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            datePicker.updateDate(it.getInt("year"), it.getInt("month"), it.getInt("date"))
        }

    }
}

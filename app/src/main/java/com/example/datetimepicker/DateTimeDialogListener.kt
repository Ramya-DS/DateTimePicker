package com.example.datetimepicker

interface DateTimeDialogListener {
    fun closeDialog()
    fun dataTimeChanged(isDate: Boolean, date: String)
}
package com.example.datetimepicker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class DateTimePagerAdapter(fragmentActivity: FragmentActivity): androidx.viewpager2.adapter.FragmentStateAdapter(fragmentActivity) {

    companion object{
        const val SIZE=2
    }
    override fun getItemCount()= SIZE

    override fun createFragment(position: Int): Fragment= when(position){
        0-> DateFragment()
        1->TimeFragment()
        else-> DateFragment()
    }
}
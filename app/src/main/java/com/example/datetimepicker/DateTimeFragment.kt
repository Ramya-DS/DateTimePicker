package com.example.datetimepicker


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class DateTimeFragment : DialogFragment() {

    lateinit var rootView: View
    lateinit var dateTimeViewPager: androidx.viewpager2.widget.ViewPager2
    lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_date_time, container, false)

        dateTimeViewPager = rootView.findViewById(R.id.date_time_viewpager)
        dateTimeViewPager.adapter = DateTimePagerAdapter(this.activity!!)

        tabLayout = rootView.findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, dateTimeViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "DATE"
                1 -> "TIME"
                else -> ""
            }
        }.attach()

        return rootView
    }


}

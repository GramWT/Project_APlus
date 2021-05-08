package com.example.project.Calendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.R
import com.example.project.databinding.CalendarDayLayoutBinding
import com.example.project.databinding.CalendarHeaderBinding
import com.example.project.databinding.FragmentEventCalendarBinding
import com.example.project.databinding.FragmentEventNotificationBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*


class EventCalendar : Fragment() {

    private lateinit var binding: FragmentEventCalendarBinding
    private lateinit var mEventCalendarModel: EventCalendarViewModel
    private lateinit var eventList:List<EventCalendar>
    private var gridLayoutManager: GridLayoutManager? = null
    private var selectedDate: LocalDate? = null
    private val week:Array<String> = arrayOf("S","M","T","W","T","F","S")


    @RequiresApi(Build.VERSION_CODES.O)
    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")

    @RequiresApi(Build.VERSION_CODES.O)
    private val titleFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")

    @RequiresApi(Build.VERSION_CODES.O)
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentEventCalendarBinding.inflate(layoutInflater)


        var adapter = CalendarAdapter()

        binding.recycleEvent.adapter = adapter

        gridLayoutManager = GridLayoutManager(requireContext(),1, LinearLayoutManager.VERTICAL,false)

        binding.recycleEvent.layoutManager = gridLayoutManager

        mEventCalendarModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)




        if (savedInstanceState == null) {
            binding.calendarView.post {
                // Show today's events initially.
                selectDate(today)
            }
        }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(20)
        val lastMonth = currentMonth.plusMonths(20)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        binding.calendarView.scrollToMonth(currentMonth)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay
            val bindingView = CalendarDayLayoutBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }

                }
            }
        }

        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {

            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                val textView = container.bindingView.calendarDayText
                val dot = container.bindingView.DotView
                container.day = day
                textView.text = day.date.dayOfMonth.toString()


                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.visibility = View.VISIBLE
                    when (day.date) {

                        today -> {
                            textView.setTextColor(R.color.white)
                            textView.setBackgroundResource(R.drawable.today_bg_calendar)
                            dot.visibility = View.INVISIBLE
                            mEventCalendarModel.readDataByDate(day.date.dayOfMonth,day.date.monthValue -1,day.date.year).observe(viewLifecycleOwner,{event ->
                                adapter.setData(event)
                            })


                        }
                        selectedDate -> {
                            textView.setTextColor(R.color.white)
                            textView.setBackgroundResource(R.drawable.select_bg_calendar)
                            dot.visibility = View.INVISIBLE
                            mEventCalendarModel.readDataByDate(day.date.dayOfMonth,day.date.monthValue -1,day.date.year).observe(viewLifecycleOwner,{event ->
                                adapter.setData(event)
                            })

                        }
                        else -> {
                            textView.setTextColor(R.color.white)
                            textView.background = null
                            mEventCalendarModel.readDataByDate(day.date.dayOfMonth,day.date.monthValue -1,day.date.year).observe(viewLifecycleOwner,{event ->
                                eventList = event
                                if (eventList.isEmpty()){
                                    dot.visibility = View.INVISIBLE
                                }
                                else if (eventList.isNotEmpty()){
                                    dot.visibility = View.VISIBLE
                                }
                            })

                        }
                    }
                } else {
                    textView.setTextColor(R.color.color_grey)
                    mEventCalendarModel.readDataByDate(day.date.dayOfMonth,day.date.monthValue -1,day.date.year).observe(viewLifecycleOwner,{event ->
                        eventList = event
                        textView.background = null
                        if (eventList.isEmpty()){
                            dot.visibility = View.INVISIBLE
                        }
                        else if (eventList.isNotEmpty()){
                            dot.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }

        binding.calendarView.monthScrollListener = {
            binding.calendarBar.text = titleFormatter.format(it.yearMonth)


            selectDate(it.yearMonth.atDay(1))
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = CalendarHeaderBinding.bind(view).legendLayout.root
        }
        binding.calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }.forEachIndexed { index, tv ->
                        tv.text = week[index]
                        tv.setTextColor(R.color.black)
                    }
                }
            }
        }


        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.calendarView.notifyDateChanged(it) }
            binding.calendarView.notifyDateChanged(date)
            binding.dateSelect.text = selectionFormatter.format(date)
        }
    }

    override fun onStart() {
        super.onStart()
    }

}
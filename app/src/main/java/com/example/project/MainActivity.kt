package com.example.project

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.project.Calendar.EventCalendar
import com.example.project.Notifications.Event.NavEventNotification
import com.example.project.Notifications.Exam.CheckExamData
import com.example.project.Notifications.Lesson.CheckLessonData
import com.example.project.Setting.NavSetting
import com.example.project.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Project)
        setContentView(mainBinding.root)
        val exam = CheckExamData()
        val lesson = CheckLessonData()
        val event = NavEventNotification()
        val calendar = EventCalendar()
        val setting = NavSetting()

        val appSettingPrefs: SharedPreferences =
            getSharedPreferences("AppSettingPrefs", MODE_PRIVATE)
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", true)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        makeCurrentFragment(exam)

        mainBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_exam -> makeCurrentFragment(exam)
                R.id.menu_lesson -> makeCurrentFragment(lesson)
                R.id.menu_event -> makeCurrentFragment(event)
                R.id.menu_calendar -> makeCurrentFragment(calendar)
                R.id.menu_setting -> makeCurrentFragment(setting)
            }
            true
        }

    }


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.fragment_fade_enter,
                R.anim.fragment_fade_exit,
                R.anim.fragment_fade_enter,
                R.anim.fragment_fade_exit
            )
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


    fun hideBottomNav() {
        bottom_navigation.isInvisible = true

    }

    fun showBottomNav() {
        bottom_navigation.isInvisible = false
    }


}
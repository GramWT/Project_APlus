package com.example.project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.project.Notifications.Event.EventNotification
import com.example.project.Notifications.Event.EventNotificationAdd
import com.example.project.Notifications.Event.NavEventNotification
import com.example.project.Notifications.Exam.ExamNotification
import com.example.project.Notifications.Lesson.LessonNotification
import com.example.project.Setting.NavSetting
import com.example.project.Setting.Setting
import com.example.project.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private val MainBunding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var parentLinearLayout: LinearLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MainBunding.root)

        val exam = ExamNotification()
        val lesson = LessonNotification()
        val event = NavEventNotification()
        val setting = NavSetting()


        makeCurrentFragment(exam)

        MainBunding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_exam -> makeCurrentFragment(exam)
                R.id.menu_lesson -> makeCurrentFragment(lesson)
                R.id.menu_event -> makeCurrentFragment(event)
                R.id.menu_setting -> makeCurrentFragment(setting)
            }
            true
        }
        
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    public fun hideBottomNav(){
        MainBunding.bottomNavigation.isInvisible = true
    }

    public fun showBottomNav(){
        MainBunding.bottomNavigation.isInvisible = false
    }










}
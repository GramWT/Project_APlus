package com.example.project.Notifications.Exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.viewpager.widget.ViewPager
import com.example.project.Adapter.TabAdapter
import com.example.project.R
import com.example.project.databinding.ActivityMainBinding
import com.example.project.databinding.FragmentSubjectTabBinding
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_subject_tab.*

class ExamNotification : Fragment() {

    lateinit var binding: FragmentSubjectTabBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectTabBinding.inflate(layoutInflater)


        val CF = childFragmentManager
        val Tab_Adapter = TabAdapter(CF)

        binding.viewpager.adapter = Tab_Adapter
        binding.tablayout.setupWithViewPager(binding.viewpager)


        return binding.root

    }

     fun hideTab(){
        binding.tablayout.visibility = View.GONE
    }


}
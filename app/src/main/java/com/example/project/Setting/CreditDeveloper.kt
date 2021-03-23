package com.example.project.Setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_credit_developer.view.*


class CreditDeveloper : Fragment() {

    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.VISIBLE
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_credit_developer, container, false)

        view.back_icon.setOnClickListener {
            val action = CreditDeveloperDirections.actionCreditDeveloper2ToSetting()
            findNavController().navigate(action)
        }

        view.aof_profile.animation = AnimationUtils.loadAnimation(view.aof_profile.context,R.anim.item_slide_left)

        view.gram_profile.animation = AnimationUtils.loadAnimation(view.gram_profile.context,R.anim.item_slide_right)

        return  view
    }


}
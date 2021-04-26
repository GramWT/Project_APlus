package com.example.project

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_preference_view.view.*


class PreferenceView : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_preference_view, container, false)
        val mainActions = context as MainActivity
        val appSettingPrefs: SharedPreferences = mainActions.getSharedPreferences("AppSettingPrefs",Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode",true)




        view.backFromPreference.setOnClickListener {
            val action = PreferenceViewDirections.actionPreferenceViewToSetting()
            findNavController().navigate(action)
            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE
        }

        if (isNightModeOn){
            view.switch2.isChecked = isNightModeOn
            view.switch2.text = "Dark"
        }else{
            view.switch2.isChecked = isNightModeOn
            view.switch2.text = "Light"
        }


        view.switch2.setOnClickListener {
            changeTheme(view)
        }

        return view
    }
    private fun changeTheme(view: View){
        val builder = AlertDialog.Builder(requireContext())
        val mainActions = context as MainActivity
        val appSettingPrefs: SharedPreferences = mainActions.getSharedPreferences("AppSettingPrefs",Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode",true)
        builder.setPositiveButton("yes"){ _, _ ->

            if (isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode",false)
                sharedPrefsEdit.apply()

                val a = activity as MainActivity
                a.bottom_navigation.visibility = View.VISIBLE
                a.bottom_navigation.selectedItemId = R.id.menu_exam

                println(1)

            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode",true)
                sharedPrefsEdit.apply()

                val a = activity as MainActivity
                a.bottom_navigation.visibility = View.VISIBLE
                a.bottom_navigation.selectedItemId = R.id.menu_exam

                println(2)

            }
            Toast.makeText(
                    requireContext(),
                    "Successfully Removed All Subjects",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No"){ _, _ ->
            view.switch2.isChecked = isNightModeOn
        }
        builder.setTitle("Change Theme ?")
        builder.setMessage("Are you sure you want to change theme?")
        builder.create().show()

    }


}
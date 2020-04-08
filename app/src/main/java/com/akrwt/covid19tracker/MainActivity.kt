package com.akrwt.covid19tracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = arrayOf(
            R.drawable.ic_covid,
            R.drawable.ic_nurse,
            R.drawable.ic_bacteria,
            R.drawable.ic_outbreak
        )

        val adapter = SliderAdapter(this, images)
        viewPager.adapter = adapter

        loadSpinner()

    }

    fun loadSpinner() {

        val stateArray = arrayOf(
            "Total",
            "Maharashtra",
            "Tamil Nadu",
            "Delhi",
            "Kerala",
            "Telangana",
            "Uttar Pradesh",
            "Rajasthan",
            "Andhra Pradesh",
            "Madhya Pradesh",
            "Karnataka",
            "Gujarat",
            "Jammu and Kashmir",
            "Haryana",
            "Punjab",
            "West Bengal",
            "Bihar",
            "Assam",
            "Uttarakhand",
            "Odisha",
            "Chandigarh",
            "Ladakh",
            "Andaman and Nicobar Islands",
            "Chhattisgarh",
            "Goa",
            "Himachal Pradesh",
            "Puducherry",
            "Jharkhand",
            "Manipur",
            "Mizoram",
            "Arunachal Pradesh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Lakshadweep",
            "Meghalaya",
            "Nagaland",
            "Sikkim",
            "Tripura"
        )

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stateArray)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val sharedPref = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("state", stateArray.get(position))
                editor.apply()
                goBtn.setOnClickListener {
                    startActivity(Intent(this@MainActivity, DetailsActivity::class.java))
                }

            }
        }
    }
}

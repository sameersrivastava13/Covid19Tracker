package com.akrwt.covid19tracker

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_details.*
import org.json.JSONObject

class DetailsActivity : AppCompatActivity() {
    private var getState:SharedPreferences ?=  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getState = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

        getData()
    }

    private fun getData() {
        val url = "https://api.covid19india.org/data.json"
        val state = getState!!.getString("state","default")
        stateTV.text = state
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            Response.Listener {response ->
                val jObj = JSONObject(response)
                val jArray = jObj.getJSONArray("statewise")
                for(i in 0 until jArray.length()){
                    val obj =jArray.getJSONObject(i)
                    if(obj.getString("state") == state){
                        tvActive.text = obj.getString("active")
                        tvConfirmed.text = obj.getString("confirmed")
                        tvRecovered.text = obj.getString("recovered")
                        tvDeaths.text = obj.getString("deaths")
                        pBar.visibility = View.INVISIBLE
                    }
                }

        }, Response.ErrorListener {
                Toast.makeText(applicationContext,it.message,Toast.LENGTH_LONG).show()
        })
        Volley.newRequestQueue(this).add(stringRequest)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        val editor = getState!!.edit()
        editor.clear()
        editor.apply()

    }
}

package com.example.mypreload

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    var myFirstRun :FirstRunSharePref?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myFirstRun = FirstRunSharePref(this)
        UserTransaction(this@MainActivity).deleteAll()
        if(myFirstRun!!.firstRun){
            val secondIntent = Intent(this,PreLoad::class.java)
            startActivity(secondIntent)
        }
        updateAdapter()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }
    private fun updateAdapter() {
        doAsync {
            var nameList = UserTransaction(this@MainActivity)
                .viewAllName().toTypedArray()
            uiThread {
                if (spinner1 != null && nameList.size != 0) {
                    val arrayAdapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_spinner_item, nameList)
                    spinner1.adapter = arrayAdapter

                }
            }
        }
    }


}

package com.example.mypreload

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_pre_load.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreLoad : AppCompatActivity() {
    private var mhs = listOf(
        User("A1","",""),User("A2","",""),User("A3","","")
        ,User("A4","",""),User("A5","",""),User("A6","","")
        ,User("A7","",""),User("A8","",""),User("A9","","")
        ,User("A10","",""),User("A11","",""),User("A12","","")
        ,User("A13","",""),User("A14","",""),User("A15","","")
        ,User("A16","",""),User("A17","",""),User("A18","","")
        ,User("A19","",""),User("A20","",""),User("A21","","")
        ,User("A22","",""),User("A23","",""),User("A24","","")
        ,User("A25","",""),User("A26","",""),User("A27","","")
        ,User("A28","",""),User("A29","",""),User("A30","","")
        ,User("A31","",""),User("A32","",""),User("A33","","")
        ,User("A34","",""),User("A35","",""),User("A36","","")
        ,User("A37","",""),User("A38","",""),User("A39","","")
        ,User("A40","",""),User("A41","",""),User("A42","","")
        ,User("A43","",""),User("A44","",""),User("A45","","")
        ,User("A46","",""),User("A47","",""),User("A48","","")
        ,User("A49","",""),User("A50","","")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_load)
        btn_yes.setOnClickListener {executeLoadData()}
        btn_no.setOnClickListener {finishActivity()}
    }
    private fun executeLoadData() {
        btn_no.isEnabled = false
        btn_yes.isEnabled = false
        myProgress.progress=0
        var database = UserTransaction(this@PreLoad)
        doAsync {
            database.beginUserTransaction()
            for (userData in mhs) {
                database.addUserTransaction(userData)
                uiThread {
                    myProgress.progress += myProgress.max/mhs.size
                    Log.w("Progress","${myProgress.progress}")
                }
            }
            database.successUserTransaction()
            database.endUserTransaction()
            uiThread {
                finishActivity()
            }
        }

    }

    fun finishActivity(){
        var myFirstRunSharePref = FirstRunSharePref(this)
        myFirstRunSharePref.firstRun = false
        this.finish()
    }
}



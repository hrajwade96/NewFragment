package com.example.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var num=0
    val manager = supportFragmentManager
    var isFragLoaded=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragOne()
        btn.setOnClickListener{
            if(isFragLoaded)
            {
                showFragTwo()
            }
            else{
                showFragOne()
            }
        }
        btn2.setOnClickListener{
            var intent = Intent(this,BottomActivity::class.java)
            startActivity(intent)
        }

    }
    fun showFragOne()
    {
        val transaction =manager.beginTransaction()
        val fragment= Fragment1()
        transaction.replace(R.id.fm,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragLoaded=true
    }
    fun showFragTwo()
    {
        val transaction =manager.beginTransaction()
        val fragment= Fragment2()
        transaction.replace(R.id.fm,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragLoaded=false
    }
}

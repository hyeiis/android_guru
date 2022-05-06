package com.example.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val time: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //1초동안 이 액티비티가 나온 후 메인 액티비티로 이동한다.
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, time)
    }
}
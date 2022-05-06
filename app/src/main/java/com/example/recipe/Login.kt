package com.example.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlin.concurrent.timer

class Login : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var japanese_food: ImageView
    lateinit var korean_food: ImageView
    lateinit var western_food: ImageView
    lateinit var chineseFood: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        viewPager = findViewById(R.id.viewPage)
        japanese_food = findViewById(R.id.japanese_food)
        korean_food = findViewById(R.id.korean_food)
        western_food = findViewById(R.id.western_food)
        chineseFood = findViewById(R.id.chineseFood)

        //일식 아이콘을 눌렀을 때 일식 레시피가 있는 곳으로 이동
        japanese_food.setOnClickListener {
            var intent = Intent(this, japanese::class.java)
            startActivity(intent)
        }
        //한식 아이콘을 눌렀을 때 한식 레시피가 있는 곳으로 이동
        korean_food.setOnClickListener {
            var intent = Intent(this, korean::class.java)
            startActivity(intent)
        }
        //양식 아이콘을 눌렀을 때 양식 레시피가 있는 곳으로 이동
        western_food.setOnClickListener {
            var intent = Intent(this, western::class.java)
            startActivity(intent)
        }
        //중식 아이콘을 눌렀을 때 중식 레시피가 있는 곳으로 이동
        chineseFood.setOnClickListener {
            var intent = Intent(this, chinese::class.java)
            startActivity(intent)
        }
    }
}
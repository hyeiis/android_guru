package com.example.recipe.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.recipe.R
import com.example.recipe.japanese

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        //inflate로 fragment_home과 연결
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //화면에 나타나는 각 ImageView를 누르면 동작하는 코드, Navigation을 사용했기 때문에 해당 navigation 화면으로 전환
        root.findViewById<ImageView>(R.id.japanese_food).setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.nav_japanese)
        }

        root.findViewById<ImageView>(R.id.korean_food).setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.nav_korean)
        }

        root.findViewById<ImageView>(R.id.western_food).setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.nav_western)
        }
        root.findViewById<ImageView>(R.id.chinese_food).setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.nav_chinese)
        }

        return root
    }
}
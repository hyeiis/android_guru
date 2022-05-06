package com.example.recipe.ui.western

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.recipe.*

class WesternFragment : Fragment() {

    //ChineseFragment와 동일한 코드(사용한 변수 명과 연결한 코틀린 파일, 레이아웃 파일만 다름)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_western, container, false)
        var japanese_list = root.findViewById<ListView>(R.id.westernlist)

        japanese_list.adapter = MyCustomAdapter(getContext())

        japanese_list.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id -> val selectItem = parent.getItemAtPosition(position)
            if(position == 0) {
                val intent = Intent(context, FrenchRecipe::class.java)
                startActivity(intent)
            } else if (position == 1){
                val intent = Intent(context, GambasRecipe::class.java)
                startActivity(intent)
            } else if (position == 2){
                val intent = Intent(context, SaladRecipe::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(context, PastaRecipe::class.java)
                startActivity(intent)
            }
        }


        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private class MyCustomAdapter(context: Context?) : BaseAdapter() {
        private val mContext : Context?

        var listname = arrayOf("감자튀김", "감바스", "포테이토 샐러드", "두부면 오일파스타")
        var listimage = arrayOf(R.drawable.french_fries, R.drawable.gambas, R.drawable.salad, R.drawable.pasta)
        var listperson = arrayOf("2인분", "3인분", "3인분", "1인분")
        init {
            mContext = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.western_list, p2,  false)

            val westernimage = rowMain.findViewById<ImageView>(R.id.western_list_image)
            westernimage.setImageResource(listimage.get(p0))
            val nameTextView = rowMain.findViewById<TextView>(R.id.recipename)
            nameTextView.text = listname.get(p0)
            val positionTextView = rowMain.findViewById<TextView>(R.id.recipenum)
            positionTextView.text = listperson.get(p0)

            return rowMain
        }

        override fun getItem(p0: Int): Any {
            val selectItem = listname.get(p0)
            return selectItem
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listname.size
        }

    }

}
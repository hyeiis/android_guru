package com.example.recipe.ui.japanese

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.recipe.*

class JapaneseFragment : Fragment() {

    //ChineseFragment와 동일한 코드(사용한 변수 명과 연결한 코틀린 파일, 레이아웃 파일만 다름)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_japanese, container, false)
        var japanese_list = root.findViewById<ListView>(R.id.japaneseList)

        japanese_list.adapter = MyCustomAdapter(getContext())

        japanese_list.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id -> val selectItem = parent.getItemAtPosition(position)
            if(position == 0) {
                val intent = Intent(context, UdonRecipe::class.java)
                startActivity(intent)
            } else if (position == 1){
                val intent = Intent(context, NebeRecipe::class.java)
                startActivity(intent)
            } else if (position == 2){
                val intent = Intent(context, chaRecipe::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(context, ButadoneRecipe::class.java)
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

        var listname = arrayOf("우동", "밀푀유나베", "차완무시", "부타동")
        var listimage = arrayOf(R.drawable.udon, R.drawable.nabe, R.drawable.cha, R.drawable.butadone)
        var listperson = arrayOf("2인분", "4인분", "4인분", "1인분")
        init {
            mContext = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.japanese_list, p2,  false)

            val japaneseimage = rowMain.findViewById<ImageView>(R.id.japanese_list_image)
            japaneseimage.setImageResource(listimage.get(p0))
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
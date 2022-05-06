package com.example.recipe.ui.chinese

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

class ChineseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //inflater로 해당 fragment_chinese와 연결, id와 연결해서 새로운 변수 생성
        val root = inflater.inflate(R.layout.fragment_chinese, container, false)
        var chinese_list = root.findViewById<ListView>(R.id.chineselist)

        //MyCustomAdapter와 연결
        chinese_list.adapter = MyCustomAdapter(getContext())

        //리스트에 나온 각 항목을 클릭했을 때 동작(intent 통해 해당 코틀린 화면으로 넘어감)
        chinese_list.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id -> val selectItem = parent.getItemAtPosition(position)
            if(position == 0) {
                val intent = Intent(context, MapaRecipe::class.java)
                startActivity(intent)
            } else if (position == 1){
                val intent = Intent(context, EggRecipe::class.java)
                startActivity(intent)
            } else if (position == 2){
                val intent = Intent(context, DumplingRecipe::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(context, DongpaRecipe::class.java)
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

    //MyCustomAdapter 생성
    private class MyCustomAdapter(context: Context?) : BaseAdapter() {
        private val mContext : Context?

        //getView에서 사용할 listname, listimage, listperson 생성
        var listname = arrayOf("마파두부", "토마토계란볶음", "깐풍만두", "동파육")
        var listimage = arrayOf(R.drawable.mapa, R.drawable.tomatoegg, R.drawable.dumpling, R.drawable.dongpa)
        var listperson = arrayOf("2인분", "2인분", "2인분", "2인분")
        init {
            mContext = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            //inflate로 chinese_list와 연결
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.chinese_list, p2,  false)

            //리스트를 눌렀을 때 몇 번째에 해당하는 값인지 받아와서 chinese_list에 있는 ImageView, TextView, TextView를 변경해줌
            val chineseimage = rowMain.findViewById<ImageView>(R.id.chinese_list_image)
            chineseimage.setImageResource(listimage.get(p0))
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
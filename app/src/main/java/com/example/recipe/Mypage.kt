package com.example.recipe


import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Mypage : AppCompatActivity() {

    lateinit var regDBManager: RegDBManager
    lateinit var sqlitedb:SQLiteDatabase

    lateinit var tvId: TextView
    lateinit var tvName: TextView
    lateinit var tvPhone: TextView
    lateinit var tvEmail:TextView
    lateinit var tvBirth:TextView

    lateinit var str_name: String
    lateinit var str_phone: String
    lateinit var str_email: String
    lateinit var str_birth: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        tvId = findViewById(R.id.edt_id)
        tvName = findViewById(R.id.edt_name)
        tvPhone = findViewById(R.id.edt_tel)
        tvEmail = findViewById(R.id.edt_email)
        tvBirth = findViewById(R.id.edt_birth)

        var mypage_id : String
        //데이터베이스 선언
        regDBManager = RegDBManager(this, "RegDB", null, 1)
        sqlitedb = regDBManager.readableDatabase

        mypage_id = intent.getStringExtra("intent_id")
        var cursor:Cursor

        //RegTBL에 있는 id가 mypage_id인 모든 정보들을 조회한다.
        cursor = sqlitedb.rawQuery("SELECT * FROM RegTBL WHERE id = '" + mypage_id + "';", null)

        while(cursor.moveToNext()){
            str_name = cursor.getString(3)
            str_phone = cursor.getString(4)
            str_email = cursor.getString(6)
            str_birth = cursor.getString(5)
        }

        cursor.close()
        sqlitedb.close()
        regDBManager.close()

        tvId.text = mypage_id
        tvName.text = str_name
        tvPhone.text = str_phone
        tvEmail.text = str_email
        tvBirth.text = str_birth + "\n"
    }
}
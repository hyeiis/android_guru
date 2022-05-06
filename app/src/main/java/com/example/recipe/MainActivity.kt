package com.example.recipe

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnRegister: Button
    lateinit var btnLogin: Button
    lateinit var idEdt: EditText
    lateinit var pwdEdt: EditText

    lateinit var RegDBManager : RegDBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnRegister = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)
        idEdt = findViewById(R.id.idEdt)
        pwdEdt = findViewById(R.id.pwdEdt)


        //회원가입 버튼을 누르면 회원가입 창으로 감.
        btnRegister.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        //로그인 버튼을 눌렀을 때 기능
        btnLogin.setOnClickListener {
            var str_id = idEdt.text.toString()
            var str_pwd = pwdEdt.text.toString()

            var result : String = ""
            RegDBManager = RegDBManager(this, "RegDB", null, 1)
            sqlitedb = RegDBManager.readableDatabase

            //데이터베이스에서 id와 pwd 비교
            var cursor : Cursor = sqlitedb.rawQuery("SELECT id, pwd FROM RegTBL", null)
            var count : Int = 0
            while(cursor.moveToNext()){
                result = (cursor.getString(0))
                count++

                //아이디와 비밀번호를 데이터베이스에 있는 데이터와 비교하여 둘 다 같으면 로그인
                if (result.equals(str_id)){
                    result = (cursor.getString(1))
                    if(result.equals(str_pwd)){
                        Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, SideBarActivity::class.java)
                        //id 정보 intent를 이용해 사이드바 액티비티로 보낸다.
                        intent.putExtra("intent_id", str_id)
                        startActivity(intent)
                        break
                    } else{ //틀리면 아이디 또는 비밀번호가 틀렸다고 메세지가 뜬다.
                            Toast.makeText(this, "아이디 또는 비밀번호가 틀렸습니다. ", Toast.LENGTH_SHORT).show()
                    }
                } else{ //회원 가입이 아예 되어 있지 않은 경우 등록되지 않았다고 뜬다.
                    if(cursor.count == count) {
                        Toast.makeText(this, "등록되지 않은 사용자입니다. 회원가입해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }


    }

}
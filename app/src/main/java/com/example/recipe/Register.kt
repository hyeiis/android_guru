package com.example.recipe

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener


class Register : AppCompatActivity() {

    lateinit var btnBack: ImageButton

    lateinit var edtId: EditText
    lateinit var edtPassword: EditText
    lateinit var edtPasswordChk: EditText
    lateinit var edtName: EditText
    lateinit var edtPhone: EditText
    lateinit var edtEmail: EditText
    lateinit var edtBirth: EditText
    lateinit var btnGo: Button

    //회원 가입 데이터베이스 선언
    lateinit var RegDBManager: RegDBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnBack = findViewById(R.id.btnBack)

        edtId = findViewById(R.id.edtId)
        edtPassword = findViewById(R.id.edtPassword)
        edtPasswordChk = findViewById(R.id.edtPasswordChk)
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        edtEmail = findViewById(R.id.edtEmail)
        edtBirth = findViewById(R.id.edtBirth)

        btnGo = findViewById(R.id.btnGo)

        RegDBManager = RegDBManager(this, "RegDB", null, 1)

        //뒤로가기 버튼을 누르면 메인 액티비티로 이동
        btnBack.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        btnGo.setOnClickListener {
            //확인 버튼을 눌렀을 때 비밀번호와 비밀번호 확인이 다르면 비밀번호가 일치하지 않습니다 라는 토스트 메시지를 띄우고 회원가입이 되지 않게 한다.
            if (!edtPassword.text.toString().equals(edtPasswordChk.text.toString())) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                edtPassword.setText("")
                edtPasswordChk.setText("")
                edtPassword.requestFocus()
            }

            var str_id = edtId.text.toString()
            var str_pwd = edtPassword.text.toString()
            var str_pwdChk = edtPasswordChk.text.toString()
            var str_name = edtName.text.toString()
            var str_phone = edtPhone.text.toString()
            var str_email = edtEmail.text.toString()
            var str_birth = edtBirth.text.toString()

            //회원 가입을 하면 정보들이 회원가입 데이터베이스에 들어가도록 한다.
            sqlitedb = RegDBManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO RegTBL VALUES ('" + str_id+ "', '" + str_pwd + "', '" + str_pwdChk + "', '" +
                    str_name + "', '" + str_phone + "', '" + str_email + "', '" + str_birth + "');")
            sqlitedb.close()

            //회원가입이 되면 메인 액티비티로 id 값과 함께 인텐트를 보낸다.
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("intent_name", str_id)
            startActivity(intent)
        }
    }
}
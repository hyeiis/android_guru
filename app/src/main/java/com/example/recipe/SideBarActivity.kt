package com.example.recipe

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SideBarActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var  RegDBManager: RegDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var header_user_name: TextView
    lateinit var header_user_info: TextView

    lateinit var str_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_bar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout) //SideBar
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //메인 액티비티에서 보낸 intent_id 값을 받는다.
        str_id = intent.getStringExtra("intent_id").toString()

        //데이터베이스 선언
        RegDBManager = RegDBManager(this, "RegDB", null, 1)
        sqlitedb = RegDBManager.readableDatabase

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_japanese, R.id.nav_western, R.id.nav_korean, R.id.nav_chinese
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.side_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.my_page -> { //사이드바에서 마이페이지를 누르면 마이페이지로 넘어간다. 마이페이지에는 회원 정보가 있다.
                val intent = Intent(this, Mypage::class.java)
                //마이페이지에 intent 값 전달
                intent.putExtra("intent_id", str_id)
                startActivity(intent)
                return true
            }
            R.id.logout -> { //사이드바에서 로그아웃을 누르면 메인 액티비티로 이동한다.
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.reg_delete -> { //회원 탈퇴. 데이터베이스에서 회원 정보 삭제
                //var str_id = idEdt.text.toString()
                var delete_id :String
                RegDBManager = RegDBManager(this, "RegDB", null, 1)
                sqlitedb = RegDBManager.readableDatabase
                delete_id = intent.getStringExtra("intent_id")
                sqlitedb.execSQL("DELETE FROM RegTBL WHERE id = '" + delete_id + "';")

                sqlitedb.close()
                RegDBManager.close()

                //탈퇴가 되면 메인 액티비티로 이동
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, delete_id + " 탈퇴되었습니다.", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        header_user_name = findViewById(R.id.header_user_name)
        header_user_info = findViewById(R.id.header_user_id)
        var header_user_id : String = ""


        //데이터베이스 연결
        RegDBManager = RegDBManager(this, "RegDB", null, 1)
        sqlitedb = RegDBManager.readableDatabase
        //intent로 넘어온 intent_id값을 header_user_id에 넣어준다.
        header_user_id = intent.getStringExtra("intent_id")

        //사이드바에 RegTBL에 있는 id와 name을 나타낸다.
        var cursor : Cursor = sqlitedb.rawQuery("SELECT id, name FROM RegTBL", null)

        var result_name : String = ""

        //intent로 받아온 아이디인 header_user_id와 RegTBL에서 가져온 id가 동일하면 RegTBL에서 가져온 name값을 result_name에 넣어준다.
        while(cursor.moveToNext()){
            result_name = (cursor.getString(0))
            if (result_name.equals(header_user_id)){
                result_name = (cursor.getString(1))
            }


        }

        //nav_header_main에 있는 TextView, TextView를 변경해준다.
        header_user_name.text = "Name : " + result_name
        header_user_info.text = "ID : " + header_user_id

        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var isLogin: Boolean = false
    lateinit var playNowButton: Button
    lateinit var logoutButton: Button
    lateinit var greetingUserTextView: TextView
    var nameOfUserLogged : String = ""
    var indexOfUserLogged: Int = 0
//    var listNameOfUser = arrayListOf<String>()
//    var listPasswordOfUser = arrayListOf<String>()
//    var listBestScoreOfUser = arrayListOf<Int>()
    var listNameOfUser: ArrayList<String> = arrayListOf<String>("Andi", "Budi","Cino")
    var listPasswordOfUser: ArrayList<String> = arrayListOf<String>("123", "123", "123")
    var listBestScoreOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)


    private fun initComponents(){
        playNowButton = findViewById(R.id.playNowButton)
        greetingUserTextView = findViewById(R.id.greetingUser)
        logoutButton = findViewById(R.id.logoutButton)
    }
    private fun initListeners(){
        playNowButton.setOnClickListener{
            Intent(this, ChoosingActivity::class.java).also{
                it.putExtra("name", this.nameOfUserLogged)
                Log.i("UserLogin", "User di Main Activity : ${this.nameOfUserLogged}")
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                startActivity(it)
            }
        }
        logoutButton.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                startActivity(it)

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        isLogin = intent.getBooleanExtra("isLogin", false)

        if(isLogin){
            this.nameOfUserLogged = intent.getStringExtra("name").toString()
            this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
            this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
            this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
            this.listBestScoreOfUser = intent.getIntegerArrayListExtra("listBestScoreUser") as ArrayList<Int>

            for (i in this.listNameOfUser){
                Log.i("intent", "User yang dikirim dari login Activity : $i")
            }
            for (j in this.listPasswordOfUser){
                Log.i("intent", "Password User yang dikirim dari login Activity : $j")
            }
            for (k in this.listBestScoreOfUser){
                Log.i("intent", "Best Score User yang dikirim dari login Activity : $k")
            }
            initListeners()
            greetingUserTextView.text = resources.getString(R.string.hiUser, this.nameOfUserLogged)
        }else{
            Toast.makeText(this, "Please login to play", Toast.LENGTH_SHORT).show()
            Intent(this, LoginActivity::class.java).also {
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                startActivity(it)
            }

        }
    }
}
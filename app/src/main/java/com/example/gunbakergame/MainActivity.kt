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
    var listNameOfUser: ArrayList<String> = arrayListOf<String>("Andi", "Budi","Cino")
    var listPasswordOfUser: ArrayList<String> = arrayListOf<String>("123", "123", "123")
    var listBestScoreEasyLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreMediumLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreHardLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)


    private fun initComponents(){
        playNowButton = findViewById(R.id.playNowButton)
        greetingUserTextView = findViewById(R.id.greetingUser)
        logoutButton = findViewById(R.id.logoutButton)
    }
    private fun initListeners(){
        playNowButton.setOnClickListener{
            Intent(this, RuleGameActivity::class.java).also{
                it.putExtra("name", this.nameOfUserLogged)
                Log.i("UserLogin", "User di Main Activity : ${this.nameOfUserLogged}")
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                startActivity(it)
            }
        }
        logoutButton.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                startActivity(it)

            }
        }
    }
    fun getIntentAll(){
        this.nameOfUserLogged = intent.getStringExtra("name").toString()
        this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreEasyLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreEasyLevelOfUser") as ArrayList<Int>
        this.listBestScoreMediumLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreMediumLevelOfUser") as ArrayList<Int>
        this.listBestScoreHardLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreHardLevelOfUser") as ArrayList<Int>

        for (i in this.listNameOfUser){
            Log.i("user", "User yang dikirim dari login Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("user", "Password User yang dikirim dari login Activity : $j")
        }
        for (k in this.listBestScoreEasyLevelOfUser){
            Log.i("user", "Best Score Easy Level User yang dikirim ke Main Activity : $k")
        }
        for (l in this.listBestScoreMediumLevelOfUser){
            Log.i("user", "Best Score Medium Level User yang dikirim ke Main Activity : $l")
        }
        for (m in this.listBestScoreHardLevelOfUser){
            Log.i("user", "Best Score Hard Level User yang dikirim ke Main Activity : $m")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        isLogin = intent.getBooleanExtra("isLogin", false)

        if(isLogin){
            getIntentAll()
            initListeners()
            greetingUserTextView.text = resources.getString(R.string.hiUser, this.nameOfUserLogged)
        }else{
            Toast.makeText(this, "Please login to play", Toast.LENGTH_SHORT).show()
            Intent(this, LoginActivity::class.java).also {
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                startActivity(it)
            }
        }
    }
}
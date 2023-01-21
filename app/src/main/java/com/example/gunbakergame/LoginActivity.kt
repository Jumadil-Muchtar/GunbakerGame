package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var nameUser: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    lateinit var pleaseRegisterTextView: TextView
    var listNameOfUser: ArrayList<String> = arrayListOf<String>("Andi", "Budi","Cino")
    var listPasswordOfUser: ArrayList<String> = arrayListOf<String>("123", "123", "123")
    var listBestScoreEasyLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreMediumLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreHardLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)


    private fun initComponents(){
        nameUser = findViewById(R.id.editTextNameUser)
        password = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        pleaseRegisterTextView = findViewById(R.id.pleaseRegister)

    }
    private fun initListeners(){

        loginButton.setOnClickListener(){
            var user1 = User(this.nameUser.text.toString(), this.password.text.toString(), arrayListOf(0,0,0))
            var loginProcess = Authority(user1)

            if (loginProcess.login(this.listNameOfUser, this.listPasswordOfUser)){
                var nameOfUser = this.nameUser.text.toString()
                Intent(this, MainActivity::class.java).also{
                    it.putExtra("isLogin", true)
                    it.putExtra("name", nameOfUser)
                    it.putExtra("index", loginProcess.indexOfUser)
                    it.putExtra("listNameUser", this.listNameOfUser)
                    it.putExtra("listPasswordUser", this.listPasswordOfUser)
                    it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                    it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                    it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)

                    Log.i("UserLogin", "User di login Activity : $nameOfUser")
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, "Name or password is false", Toast.LENGTH_SHORT).show()
            }
        }
        pleaseRegisterTextView.setOnClickListener(){
            Intent(this, RegistrationActivity::class.java).also{
                it.putExtra("listNameUser", this.listNameOfUser)
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                startActivity(it)
            }
        }


    }
    fun getIntentAll(){
        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreEasyLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreEasyLevelOfUser") as ArrayList<Int>
        this.listBestScoreMediumLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreMediumLevelOfUser") as ArrayList<Int>
        this.listBestScoreHardLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreHardLevelOfUser") as ArrayList<Int>

        for (i in this.listNameOfUser){
            Log.i("user", "User yang dikirim ke login Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("user", "Password User yang dikirim ke login Activity : $j")
        }
        for (k in this.listBestScoreEasyLevelOfUser){
            Log.i("user", "Best Score Easy Level User yang dikirim ke login Activity : $k")
        }
        for (l in this.listBestScoreMediumLevelOfUser){
            Log.i("user", "Best Score Medium Level User yang dikirim ke login Activity : $l")
        }
        for (l in this.listBestScoreHardLevelOfUser){
            Log.i("user", "Best Score Hard Level User yang dikirim ke login Activity : $l")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initComponents()
        getIntentAll()
        initListeners()
    }
}
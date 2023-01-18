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
//    lateinit var listNameOfUser: ArrayList<String>
//    lateinit var listPasswordOfUser: ArrayList<String>
//    lateinit var listBestScoreOfUser: ArrayList<Int>
    var listNameOfUser: ArrayList<String> = arrayListOf<String>("Andi", "Budi","Cino")
    var listPasswordOfUser: ArrayList<String> = arrayListOf<String>("123", "123", "123")
    var listBestScoreOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)


    private fun initComponents(){
        nameUser = findViewById(R.id.editTextNameUser)
        password = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        pleaseRegisterTextView = findViewById(R.id.pleaseRegister)

    }
    private fun initListeners(){

        loginButton.setOnClickListener(){
            var user1 = User(this.nameUser.text.toString(), this.password.text.toString(), 0)
            var loginProcess = Authority(user1)

            if (loginProcess.login(this.listNameOfUser, this.listPasswordOfUser)){
                var nameOfUser = this.nameUser.text.toString()
                Intent(this, MainActivity::class.java).also{
                    it.putExtra("isLogin", true)
                    it.putExtra("name", nameOfUser)
                    it.putExtra("index", loginProcess.indexOfUser)
                    it.putExtra("listNameUser", this.listNameOfUser)
                    it.putExtra("listPasswordUser", this.listPasswordOfUser)
                    it.putExtra("listBestScoreUser", this.listBestScoreOfUser)

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
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                startActivity(it)
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initComponents()

        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreOfUser = intent.getIntegerArrayListExtra("listBestScoreUser") as ArrayList<Int>

        for (i in this.listNameOfUser){
            Log.i("intent", "User yang dikirim ke login Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("intent", "Password User yang dikirim ke login Activity : $j")
        }
        for (k in this.listBestScoreOfUser){
            Log.i("intent", "Best Score User yang dikirim ke login Activity : $k")
        }

        initListeners()
    }


}
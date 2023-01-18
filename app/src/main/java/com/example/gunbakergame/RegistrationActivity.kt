package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class RegistrationActivity : AppCompatActivity() {
    lateinit var nameUserEditText: EditText
    lateinit var passwordUserEditText: EditText
    lateinit var confirmationPasswordEditText: EditText
    lateinit var registerButton: Button
    lateinit var pleaseLoginTextView: TextView

    var listNameUser: ArrayList<String> = arrayListOf<String>("Andi", "Budi","Cino")
    var listPasswordUser: ArrayList<String> = arrayListOf<String>("123", "123", "123")
    var listBestcoreUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)

    fun initComponents(){
        nameUserEditText = findViewById(R.id.editTextNameUser)
        passwordUserEditText = findViewById(R.id.editTextPassword)
        confirmationPasswordEditText = findViewById(R.id.editTextConfirmationPassword)
        registerButton = findViewById(R.id.registerButton)
        pleaseLoginTextView = findViewById(R.id.pleaseLogin)
    }
    fun initListeners(){
        registerButton.setOnClickListener(){
            Log.i("intent", "Click register Button")
            var password: String = this.passwordUserEditText.text.toString()
            var cpassword: String = this.confirmationPasswordEditText.text.toString()
            var name: String = this.nameUserEditText.text.toString()
            Log.i("intent", "Name : $name, Pass: $password, CPass: $cpassword")

            if (password.equals(cpassword)) {
                Log.i("intent", "Password is confirmed")
                val user1 = User(name, password, 0)
                Log.i("intent", "User is maked")
                val auth = Authority(user1)
                Log.i("intent", "Authority is maked")
                var issRegisteredIn = auth.isRegisteredIn(this.listNameUser)
                Log.i("intent", "Is $name have been registered? : $issRegisteredIn")
                if (auth.isRegisteredIn(this.listNameUser)){
                    Log.i("intent", "This user have registered, please login")
                    Toast.makeText(this, "This user have registered, please login", Toast.LENGTH_SHORT).show()
                }else {
                    this.listNameUser.add(this.nameUserEditText.text.toString())
                    this.listPasswordUser.add(this.passwordUserEditText.text.toString())
                    this.listBestcoreUser.add(0)
                    Toast.makeText(this, "Registration is succeeded, please login now", Toast.LENGTH_SHORT).show()
                    Log.i("intent", "Registration is succeeded, please login now")
                    Intent(this, LoginActivity::class.java).also{
                        it.putExtra("listNameUser", this.listNameUser)
                        it.putExtra("listPasswordUser", this.listPasswordUser)
                        it.putExtra("listBestScoreUser", this.listBestcoreUser)
                        startActivity(it)
                    }
                }
            }else{
                Toast.makeText(this, "Hopefully the password and password confirmation is same", Toast.LENGTH_SHORT).show()
            }


        }
        pleaseLoginTextView.setOnClickListener(){
            Intent(this, LoginActivity::class.java).also{
                it.putExtra("listNameUser", this.listNameUser)
                it.putExtra("listPasswordUser", this.listPasswordUser)
                it.putExtra("listBestScoreUser", this.listBestcoreUser)
                startActivity(it)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initComponents()

        this.listNameUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestcoreUser = intent.getIntegerArrayListExtra("listBestScoreUser") as ArrayList<Int>

        initListeners()
    }
}
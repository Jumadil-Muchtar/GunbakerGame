package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class RuleGameActivity : AppCompatActivity() {

    lateinit var startNowButton: Button
    lateinit var easyRadioButton: RadioButton
    lateinit var mediumnRadioButton: RadioButton
    lateinit var hardRadioButton: RadioButton
    var nameOfUserLogged: String = ""
    var indexOfUserLogged: Int = 0
    var timePlay: Int = -1
    var listNameOfUser = arrayListOf<String>()
    var listPasswordOfUser = arrayListOf<String>()
    var listBestScoreEasyLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listBestScoreMediumLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listBestScoreHardLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listRandomNumber: ArrayList<Int> = arrayListOf<Int>()
    var level: String = ""

    fun initComponents() {
        startNowButton = findViewById(R.id.startNowButton)
        easyRadioButton = findViewById(R.id.easyRadioButton)
        mediumnRadioButton = findViewById(R.id.mediumRadioButton)
        hardRadioButton = findViewById(R.id.hardRadioButton)
    }

    fun initListeners() {
        easyRadioButton.setOnClickListener() {
            this.level = "easy"
            this.timePlay = -1
        }
        mediumnRadioButton.setOnClickListener() {
            this.level = "medium"
            this.timePlay = 10
        }
        hardRadioButton.setOnClickListener() {
            this.level = "hard"
            this.timePlay = 5
        }
        startNowButton.setOnClickListener() {
            if (this.level.equals("")) {
                Toast.makeText(this, "Please select one of the option level", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var randomNumber  = RandomNumber(this.level)
                this.listRandomNumber = randomNumber.startRandom()
                Log.i("randomNumber", "random number ${this.listRandomNumber}")

                Intent(this, ChoosingActivity::class.java).also {
                    it.putExtra("name", this.nameOfUserLogged)
                    it.putExtra("index", this.indexOfUserLogged)
                    it.putExtra("listNameUser", this.listNameOfUser)
                    it.putExtra("listPasswordUser", this.listPasswordOfUser)
                    it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                    it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                    it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                    it.putExtra("level", this.level)
                    it.putExtra("playCounter", 1)
                    it.putExtra("timePlay", this.timePlay)
                    it.putExtra("listRandomNumber", this.listRandomNumber)

                    Log.i("randomNumber", "Random Number di RuleGame Activity : ${this.listRandomNumber.toList()}")
                    Log.i("UserLogin", "User di rulegame Activity : ${this.nameOfUserLogged}")
                    Log.i("level", "Level di rulegame Activity : ${this.level}")

                    startActivity(it)
                }
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

        for (i in this.listNameOfUser) {
            Log.i("user", "User yang dikirim ke Rule Activity : $i")
        }
        for (j in this.listPasswordOfUser) {
            Log.i("user", "Password User yang dikirim ke Rule Activity : $j")
        }
        for (k in this.listBestScoreEasyLevelOfUser){
            Log.i("user", "Best Score Easy Level User yang dikirim ke Rule Activity : $k")
        }
        for (l in this.listBestScoreMediumLevelOfUser){
            Log.i("user", "Best Score Medium Level User yang dikirim ke Rule Activity : $l")
        }
        for (m in this.listBestScoreHardLevelOfUser){
            Log.i("user", "Best Score Hard Level User yang dikirim ke Rule Activity : $m")
        }
        Log.i("timePlay", "Time Play : ${this.timePlay} in Rule Game Activity")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule_game)
        initComponents()
        getIntentAll()
        initListeners()
    }
}
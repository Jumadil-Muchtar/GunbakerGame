package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class ChoosingActivity : AppCompatActivity() {

    lateinit var rockImageButton : ImageButton
    lateinit var paperImageButton: ImageButton
    lateinit var scissorsImageButton: ImageButton
    lateinit var yourScoreTextView: TextView
    lateinit var countDownTextView: TextView
    lateinit var playStateTextView: TextView
    var score: Int = 0
    var nameOfUserLogged : String = ""
    var level: String = ""
    var indexOfUserLogged: Int = 0
    var playerCounter: Int = 0
    var timePlay: Int = -1
    var choosingState: Boolean = false
    var listNameOfUser = arrayListOf<String>()
    var listPasswordOfUser = arrayListOf<String>()
    var listBestScoreEasyLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listBestScoreMediumLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listBestScoreHardLevelOfUser: ArrayList<Int> = arrayListOf<Int>()
    var listRandomNumber: ArrayList<Int> = arrayListOf<Int>()


    private fun initComponents(){
        rockImageButton = findViewById(R.id.rockImageButton)
        paperImageButton = findViewById(R.id.paperImageButton)
        scissorsImageButton = findViewById(R.id.scissorsImageButton)
        yourScoreTextView = findViewById(R.id.yourScoreTextView)
        countDownTextView = findViewById(R.id.countDownTextView)
        playStateTextView = findViewById(R.id.playStateTextView)
    }
    private fun initListeners(){
        rockImageButton.setOnClickListener(){
            this.choosingState = true
            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "ROCK")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser)
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                it.putExtra("playCounter", this.playerCounter)
                it.putExtra("level", this.level)
                it.putExtra("timePlay", this.timePlay)
                it.putExtra("listRandomNumber", this.listRandomNumber)
                Log.i("randomNumber", "Random Number di RuleGame Activity : ${this.listRandomNumber.toList()}")
                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")

                startActivity(it)
            }
        }
        paperImageButton.setOnClickListener(){
            this.choosingState = true


            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "PAPER")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                it.putExtra("playCounter", this.playerCounter)
                it.putExtra("level", this.level)
                it.putExtra("timePlay", this.timePlay)
                it.putExtra("listRandomNumber", this.listRandomNumber)
                Log.i("randomNumber", "Random Number di RuleGame Activity : ${this.listRandomNumber.toList()}")

                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")
                startActivity(it)

            }
        }
        scissorsImageButton.setOnClickListener(){
            this.choosingState = true
            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "SCISSORS")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                it.putExtra("playCounter", this.playerCounter)
                it.putExtra("level", this.level)
                it.putExtra("timePlay", this.timePlay)
                it.putExtra("listRandomNumber", this.listRandomNumber)
                Log.i("randomNumber", "Random Number di RuleGame Activity : ${this.listRandomNumber.toList()}")
                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")
                startActivity(it)
            }
        }
    }

    fun gameOver(){
        Intent(this, ScoringActivity::class.java).also{
            it.putExtra("optionFinger", "ROCK")
            it.putExtra("score", this.score)
            it.putExtra("name", this.nameOfUserLogged)
            it.putExtra("index", this.indexOfUserLogged)
            it.putExtra("listNameUser", this.listNameOfUser)
            it.putExtra("listPasswordUser", this.listPasswordOfUser)
            it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
            it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
            it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
            it.putExtra("listRandomNumber", this.listRandomNumber)
            it.putExtra("playCounter", this.playerCounter)
            it.putExtra("isGameOver", true)
            it.putExtra("level", this.level)
            Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")
            startActivity(it)
        }
    }
    fun getIntentAll(){
        this.score = intent.getIntExtra("score" ,0)
        this.nameOfUserLogged = intent.getStringExtra("name").toString()
        this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
        Log.i("index", "Index of User Logged ${indexOfUserLogged} in Choosing Activity")

        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreEasyLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreEasyLevelOfUser") as ArrayList<Int>
        this.listBestScoreMediumLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreMediumLevelOfUser") as ArrayList<Int>
        this.listBestScoreHardLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreHardLevelOfUser") as ArrayList<Int>
        this.playerCounter = intent.getIntExtra("playCounter", 0)
        this.listRandomNumber = intent.getIntegerArrayListExtra("listRandomNumber") as ArrayList<Int>
        Log.i("playerCounter", "player counter : ${this.playerCounter} di activity Choosing")
        this.level = intent.getStringExtra("level").toString()
        Log.i("level", "level : ${this.level} di activity Choosing")
        this.timePlay = intent.getIntExtra("timePlay", -1)
        Log.i("timePlay", "Time Play di activity choosing : ${this.timePlay}")
        Log.i("randomNumber", "Putaran ke-${this.playerCounter}")
        Log.i("randomNumber", "Random Number di Choosing Activity : ${this.listRandomNumber}")
        for (i in this.listNameOfUser){
            Log.i("user", "User yang dikirim ke choosing Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("user", "Password User yang dikirim ke choosing Activity : $j")
        }
        for (k in this.listBestScoreEasyLevelOfUser){
            Log.i("user", "Best Score Easy Level User yang dikirim ke Choosing Activity : $k")
        }
        for (l in this.listBestScoreMediumLevelOfUser){
            Log.i("user", "Best Score Medium Level User yang dikirim ke Choosing Activity : $l")
        }
        for (m in this.listBestScoreHardLevelOfUser){
            Log.i("user", "Best Score Hard Level User yang dikirim ke Choosing Activity : $m")
        }
    }
    fun updateComponents(){
        when(this.level){
            "easy" -> playStateTextView.text = "Play : ${this.playerCounter} / 10"
            "medium" -> playStateTextView.text = "Play : ${this.playerCounter} / 25"
            "hard" -> playStateTextView.text = "Play : ${this.playerCounter} / 50"
        }
        yourScoreTextView.text = resources.getString(R.string.your_score, this.score.toString())


        if(timePlay <= 0){
            countDownTextView.visibility = View.GONE
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosing)
        initComponents()
        getIntentAll()
        updateComponents()
        initListeners()

        if(this.timePlay > 0){
            var timePLayy = (this.timePlay * 1000).toLong()
            object : CountDownTimer(timePLayy,  1000) {
                override fun onTick(timerr: Long) {
                    countDownTextView.text = "${timerr/1000}s"
                }
                override fun onFinish() {
                    if(!choosingState){
                        gameOver()
                    }
                }
            }.start()
        }
    }
}
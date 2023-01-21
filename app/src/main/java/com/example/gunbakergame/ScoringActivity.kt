package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.log

class ScoringActivity : AppCompatActivity() {
    lateinit var playerFingerImageView: ImageView
    lateinit var computerFingerImageView: ImageView
    lateinit var resultMessageTextView: TextView
    lateinit var resultImageView: ImageView
    lateinit var bestScoreTextView: TextView
    lateinit var scoreTextView: TextView
    lateinit var playAgainButton: Button
    lateinit var exitButton: Button
    lateinit var matchTextView: TextView
    lateinit var activityScoringLinearLayout: LinearLayout
    lateinit var newGame: Game
    var score: Int = 0
    var level: String = ""
    var optionFinger: String = ""
    var playerCounter: Int = 0
    var timePlay: Int = -1
    var nameOfUserLogged: String = ""
    var indexOfUserLogged: Int = 0
    var isGameOver: Boolean = false
    var listNameOfUser = arrayListOf<String>()
    var listPasswordOfUser = arrayListOf<String>()
    var listBestScoreEasyLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreMediumLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listBestScoreHardLevelOfUser: ArrayList<Int> = arrayListOf<Int>(0, 0, 0)
    var listRandomNumber: ArrayList<Int> = arrayListOf<Int>()



    fun initComponents(){
        playerFingerImageView = findViewById(R.id.playerFingerImageView)
        computerFingerImageView = findViewById(R.id.computerFingerImageView)
        resultImageView = findViewById(R.id.resultImageView)
        bestScoreTextView = findViewById(R.id.bestScoreTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        exitButton = findViewById(R.id.exitButton)
        resultMessageTextView = findViewById(R.id.resultMessageTextView)
        activityScoringLinearLayout = findViewById(R.id.activityScoringLinearLayout)
        matchTextView = findViewById(R.id.matchTextView)
    }

    fun initListeners(game: Game){
        if (!this.isGameOver){
            playAgainButton.setOnClickListener(){
                Intent(this, ChoosingActivity::class.java).also{
                    it.putExtra("score", this.score)
                    it.putExtra("name", this.nameOfUserLogged)
                    it.putExtra("index", this.indexOfUserLogged)
                    it.putExtra("listNameUser", this.listNameOfUser )
                    it.putExtra("listPasswordUser", this.listPasswordOfUser)
                    it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                    it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                    it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                    it.putExtra("playCounter", this.playerCounter+1)
                    it.putExtra("level", this.level)
                    Log.i("intent2", "Time Play will be sent to Choosing Activity : ${this.timePlay}")
                    it.putExtra("timePlay", this.timePlay)
                    it.putExtra("listRandomNumber", this.listRandomNumber)
                    Log.i("randomNumber", "Random Number di RuleGame Activity : ${this.listRandomNumber.toList()}")
                    Log.i("UserLogin", "User di scoring Activity : ${this.nameOfUserLogged}")
                    startActivity(it)
                }
            }
        }

        exitButton.setOnClickListener(){
            Intent(this, MainActivity::class.java).also{
                it.putExtra("isLogin", true)
//                it.putExtra("name", game.user.getName())
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreEasyLevelOfUser", this.listBestScoreEasyLevelOfUser)
                it.putExtra("listBestScoreMediumLevelOfUser", this.listBestScoreMediumLevelOfUser)
                it.putExtra("listBestScoreHardLevelOfUser", this.listBestScoreHardLevelOfUser)
                Log.i("UserLogin", "User di scoring Activity : ${this.nameOfUserLogged}")
                startActivity(it)
            }
        }
    }
    fun updateComponents(optionFingerParams:String, randomFingerParams:String, state:String, scoreUser: Int, bestScoreUser: Int, user: User ){
        Log.i("PZN", "Your option is $optionFingerParams")
        if (this.isGameOver){
            matchTextView.visibility = View.GONE
            activityScoringLinearLayout.visibility = View.GONE
            playAgainButton.visibility = View.GONE
            resultMessageTextView.text = "Game Over!"

            Log.i("cekLevel", "${this.level}")


                when(this.level){
                    "easy" -> {
                        if (this.score > this.listBestScoreEasyLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.win)
                        }else if(this.score == this.listBestScoreEasyLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.draw)
                        }else{
                            resultImageView.setImageResource(R.drawable.lose)
                        }
                    }
                    "medium" -> {
                        if (this.score > this.listBestScoreMediumLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.win)
                        }else if(this.score == this.listBestScoreMediumLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.draw)
                        }else{
                            resultImageView.setImageResource(R.drawable.lose)
                        }
                    }
                    "hard" -> {
                        if (this.score > this.listBestScoreHardLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.win)
                        }else if(this.score == this.listBestScoreHardLevelOfUser[this.indexOfUserLogged]){
                            resultImageView.setImageResource(R.drawable.draw)
                        }else{
                            resultImageView.setImageResource(R.drawable.lose)
                        }
                    }
                }
        }
        else{
            when (optionFingerParams){
                "ROCK" -> playerFingerImageView.setImageResource(R.drawable.rock_icon)
                "SCISSORS" -> playerFingerImageView.setImageResource(R.drawable.scissor_icon)
                "PAPER" -> playerFingerImageView.setImageResource(R.drawable.paper_icon)
            }
            when (randomFingerParams){
                "ROCK" -> computerFingerImageView.setImageResource(R.drawable.rock_icon)
                "SCISSORS" -> computerFingerImageView.setImageResource(R.drawable.scissor_icon)
                "PAPER" -> computerFingerImageView.setImageResource(R.drawable.paper_icon)
            }
            when (state){
                "WIN" -> {
                    resultImageView.setImageResource(R.drawable.win)
                    resultMessageTextView.text = resources.getString(R.string.resultGameMessage1, state)
                }
                "DRAW" -> {
                    resultImageView.setImageResource(R.drawable.draw)
                    resultMessageTextView.text = resources.getString(R.string.resultGameMessage2, state)
                }
                "LOSE" -> {
                    resultImageView.setImageResource(R.drawable.lose)
                    resultMessageTextView.text = resources.getString(R.string.resultGameMessage1, state)
                }
            }
        }
        scoreTextView.text = resources.getString(R.string.your_score, scoreUser.toString())
        bestScoreTextView.text = resources.getString(R.string.best_score, bestScoreUser.toString())

    }
    fun checkGameOver(){

        if (this.newGame.isContinue()){
            this.isGameOver = intent.getBooleanExtra("isGameOver", false)
        }else{
            this.isGameOver = !this.newGame.isContinue()
        }

        Log.i("intent", "Is Game Over : ${this.isGameOver} in Scoring Activity")
    }
    fun getIntentAll(){
        this.optionFinger = intent.getStringExtra("optionFinger").toString()
        this.score = intent.getIntExtra("score", 0)
        this.nameOfUserLogged = intent.getStringExtra("name").toString()
        this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
        Log.i("index", "Index of User Logged ${indexOfUserLogged} in Scoring Activity")
        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreEasyLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreEasyLevelOfUser") as ArrayList<Int>
        this.listBestScoreMediumLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreMediumLevelOfUser") as ArrayList<Int>
        this.listBestScoreHardLevelOfUser = intent.getIntegerArrayListExtra("listBestScoreHardLevelOfUser") as ArrayList<Int>
        this.playerCounter = intent.getIntExtra("playCounter", 0)
        this.listRandomNumber = intent.getIntegerArrayListExtra("listRandomNumber") as ArrayList<Int>

        Log.i("playerCounter", "player counter : ${this.playerCounter} di activity scoring")
        this.level = intent.getStringExtra("level").toString()
        Log.i("level", "level : ${this.level} di activity scoring")
        this.timePlay = intent.getIntExtra("timePlay", -1)
        Log.i("timePlay", "Time Play di Scoring Activity : ${this.timePlay}")

        for (i in this.listNameOfUser){
            Log.i("user", "User yang dikirim ke scoring Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("user", "Password User yang dikirim ke scoring Activity : $j")
        }
        for (k in this.listBestScoreEasyLevelOfUser){
            Log.i("user", "Best Score Easy Level User yang dikirim ke Scoring Activity : $k")
        }
        for (l in this.listBestScoreMediumLevelOfUser){
            Log.i("user", "Best Score Medium Level User yang dikirim ke Scoring Activity : $l")
        }
        for (m in this.listBestScoreHardLevelOfUser){
            Log.i("user", "Best Score Hard Level User yang dikirim ke Scoring Activity : $m")
        }
    }
    fun gameProcessing(){
        this.newGame = Game(this.optionFinger, this.level, this.score, User(this.listNameOfUser[this.indexOfUserLogged], this.listPasswordOfUser[this.indexOfUserLogged], arrayListOf(this.listBestScoreEasyLevelOfUser[this.indexOfUserLogged], this.listBestScoreMediumLevelOfUser[this.indexOfUserLogged], this.listBestScoreHardLevelOfUser[this.indexOfUserLogged])), this.playerCounter, this.listRandomNumber)
        this.timePlay = newGame.getTimeOfRound()
        Log.i("timePlay","Time Play from getTimeOfRound is ${this.timePlay} in Scoring Activity")
        when(this.level){
            "easy" -> this.listBestScoreEasyLevelOfUser[this.indexOfUserLogged] = newGame.user.getBestScore(0)
            "easy" -> this.listBestScoreEasyLevelOfUser[this.indexOfUserLogged] = newGame.user.getBestScore(0)
            "medium" -> this.listBestScoreMediumLevelOfUser[this.indexOfUserLogged] = newGame.user.getBestScore(1)
            "hard" -> this.listBestScoreHardLevelOfUser[this.indexOfUserLogged] = newGame.user.getBestScore(2)
            else -> Log.i("level","This isn't level of the game")
        }
        this.score = newGame.score
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoring)
        initComponents()
        getIntentAll()
        gameProcessing()
        checkGameOver()
        initListeners(newGame)
        updateComponents(newGame.optionFinger, newGame.randomFinger, newGame.state, newGame.score, newGame.user.getBestScore(this.level), newGame.user)
    }
}
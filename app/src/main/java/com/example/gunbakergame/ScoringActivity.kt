package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
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
    var score: Int = 0
    var nameOfUserLogged: String = ""
    var indexOfUserLogged: Int = 0
    var listNameOfUser = arrayListOf<String>()
    var listPasswordOfUser = arrayListOf<String>()
    var listBestScoreOfUser = arrayListOf<Int>()


    fun initComponents(){
        playerFingerImageView = findViewById(R.id.playerFingerImageView)
        computerFingerImageView = findViewById(R.id.computerFingerImageView)
        resultImageView = findViewById(R.id.resultImageView)
        bestScoreTextView = findViewById(R.id.bestScoreTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        exitButton = findViewById(R.id.exitButton)
        resultMessageTextView = findViewById(R.id.resultMessageTextView)
    }

    fun initListeners(game: Game){
        playAgainButton.setOnClickListener(){
            Intent(this, ChoosingActivity::class.java).also{
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                Log.i("UserLogin", "User di scoring Activity : ${this.nameOfUserLogged}")
                startActivity(it)
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
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                Log.i("UserLogin", "User di scoring Activity : ${this.nameOfUserLogged}")

                startActivity(it)
            }
        }
    }
    fun updateComponents(optionFingerParams:String, randomFingerParams:String, state:String, scoreUser: Int, bestScoreUser: Int, user: User ){
        Log.i("PZN", "Your option is $optionFingerParams")

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

        scoreTextView.text = resources.getString(R.string.your_score, scoreUser.toString())
        bestScoreTextView.text = resources.getString(R.string.best_score, bestScoreUser.toString())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoring)
        initComponents()


        val optionFingerr = intent.getStringExtra("optionFinger").toString()
        this.score = intent.getIntExtra("score", 0)
        this.nameOfUserLogged = intent.getStringExtra("name").toString()
        this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreOfUser = intent.getIntegerArrayListExtra("listBestScoreUser") as ArrayList<Int>

        for (i in this.listNameOfUser){
            Log.i("intent", "User yang dikirim ke scoring Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("intent", "Password User yang dikirim ke scoring Activity : $j")
        }
        for (k in this.listBestScoreOfUser){
            Log.i("intent", "Best Score User yang dikirim ke scoring Activity : $k")
        }
        var newGame = Game(optionFingerr,this.score, User(this.listNameOfUser[this.indexOfUserLogged], this.listPasswordOfUser[this.indexOfUserLogged], this.listBestScoreOfUser[this.indexOfUserLogged]))

        this.listBestScoreOfUser[this.indexOfUserLogged] = newGame.user.getBestScore()
        this.score = newGame.score

        initListeners(newGame)


        updateComponents(newGame.optionFinger, newGame.randomFinger, newGame.state, newGame.score, newGame.user.getBestScore(), newGame.user)




    }
}
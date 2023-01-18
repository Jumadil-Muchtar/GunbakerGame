package com.example.gunbakergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

class ChoosingActivity : AppCompatActivity() {
    lateinit var rockImageButton : ImageButton
    lateinit var paperImageButton: ImageButton
    lateinit var scissorsImageButton: ImageButton
    lateinit var yourScoreTextView: TextView
    var score: Int = 0
    var nameOfUserLogged : String = ""
    var indexOfUserLogged: Int = 0
    var listNameOfUser = arrayListOf<String>()
    var listPasswordOfUser = arrayListOf<String>()
    var listBestScoreOfUser = arrayListOf<Int>()

    private fun initComponents(){
        rockImageButton = findViewById(R.id.rockImageButton)
        paperImageButton = findViewById(R.id.paperImageButton)
        scissorsImageButton = findViewById(R.id.scissorsImageButton)
        yourScoreTextView = findViewById(R.id.yourScoreTextView)
    }
    private fun initListeners(){
        rockImageButton.setOnClickListener(){
            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "ROCK")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")
                startActivity(it)
            }
        }
        paperImageButton.setOnClickListener(){
            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "PAPER")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")
                startActivity(it)

            }
        }
        scissorsImageButton.setOnClickListener(){
            Intent(this, ScoringActivity::class.java).also{
                it.putExtra("optionFinger", "SCISSORS")
                it.putExtra("score", this.score)
                it.putExtra("name", this.nameOfUserLogged)
                it.putExtra("index", this.indexOfUserLogged)
                it.putExtra("listNameUser", this.listNameOfUser )
                it.putExtra("listPasswordUser", this.listPasswordOfUser)
                it.putExtra("listBestScoreUser", this.listBestScoreOfUser)
                Log.i("UserLogin", "User di choosing Activity : ${this.nameOfUserLogged}")

                startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosing)
        initComponents()


        this.score = intent.getIntExtra("score" ,0)
        this.nameOfUserLogged = intent.getStringExtra("name").toString()
        this.indexOfUserLogged = intent.getIntExtra("index", 0).toInt()
        this.listNameOfUser = intent.getStringArrayListExtra("listNameUser") as ArrayList<String>
        this.listPasswordOfUser = intent.getStringArrayListExtra("listPasswordUser") as ArrayList<String>
        this.listBestScoreOfUser = intent.getIntegerArrayListExtra("listBestScoreUser") as ArrayList<Int>

        for (i in this.listNameOfUser){
            Log.i("intent", "User yang dikirim ke choosing Activity : $i")
        }
        for (j in this.listPasswordOfUser){
            Log.i("intent", "Password User yang dikirim ke choosing Activity : $j")
        }
        for (k in this.listBestScoreOfUser){
            Log.i("intent", "Best Score User yang dikirim ke choosing Activity : $k")
        }

        yourScoreTextView.text = resources.getString(R.string.your_score, this.score.toString())
        initListeners()

    }
}
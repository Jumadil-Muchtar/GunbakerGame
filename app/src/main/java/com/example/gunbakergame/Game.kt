package com.example.gunbakergame

import kotlin.random.Random

class Game(optionFinger: String, scoreParams: Int, userObj: User) {
    val user: User = userObj
    var score: Int = scoreParams
    val optionFinger: String = optionFinger
    var randomFinger: String = ""
    var state: String = ""
    val listFinger = arrayOf<String>("ROCK", "SCISSORS", "PAPER")

    init {
        computerChoosing()
        matching()
        updateBestScoreUser()
    }

    fun computerChoosing(){
        var randomNumber: Int =  Random.nextInt(0, 2)
        this.randomFinger = this.listFinger[randomNumber]
    }
    fun matching(){
        if (this.optionFinger.equals(this.randomFinger)){
            this.state = "DRAW"
            this.score += 1
        }else if(this.optionFinger.equals(this.listFinger[0]) && this.randomFinger.equals(this.listFinger[1])){
            this.state = "WIN"
            this.score += 3
        }else if(this.optionFinger.equals(this.listFinger[0]) && this.randomFinger.equals(this.listFinger[2])){
            this.state = "LOSE"
            this.score -= 1
        }else if(this.optionFinger.equals(this.listFinger[1]) && this.randomFinger.equals(this.listFinger[0])){
            this.state = "LOSE"
            this.score -= 1
        }else if(this.optionFinger.equals(this.listFinger[1]) && this.randomFinger.equals(this.listFinger[2])){
            this.state = "WIN"
            this.score += 3
        }else if(this.optionFinger.equals(this.listFinger[2]) && this.randomFinger.equals(this.listFinger[0])){
            this.state = "WIN"
            this.score += 3
        }else if(this.optionFinger.equals(this.listFinger[2]) && this.randomFinger.equals(this.listFinger[1])){
            this.state = "LOSE"
            this.score -= 1
        }
    }
    fun updateBestScoreUser(){
        if (this.score > user.getBestScore()){
            user.setBestScore(this.score)
        }
    }



}
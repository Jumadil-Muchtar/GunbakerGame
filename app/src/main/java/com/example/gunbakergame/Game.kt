package com.example.gunbakergame

import kotlin.random.Random

class Game(optionFinger: String, levelParams: String, scoreParams: Int, userObj: User, countParams: Int, listRandomNumber: ArrayList<Int>) {
    val user: User = userObj
    var score: Int = scoreParams
    val optionFinger: String = optionFinger
    var randomFinger: String = ""
    var state: String = ""
    var level: String = levelParams
    val listFinger = arrayOf<String>("ROCK", "SCISSORS", "PAPER")
    var listLevel = arrayOf<String>("easy", "medium", "hard")
    var numberOfRounds= arrayOf(10, 25, 50)
    var countOfRounds: Int = countParams
    val time = arrayOf(-1, 10, 5)
    val listRandomNumber: ArrayList<Int> = listRandomNumber


    init {
        computerChoosing()
        matching()
        updateBestScoreUser()
    }

    fun computerChoosing(){
        var randomOption: Int =  this.listRandomNumber[this.countOfRounds-1]
        this.randomFinger = this.listFinger[randomOption]
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
        for (i in 0..2){
            if (this.level.equals(listLevel[i]) && this.score > user.getBestScore(i) && this.countOfRounds == this.numberOfRounds[i]){
                user.setBestScore(i, this.score)
                break
            }
        }
    }
    fun getTimeOfRound(): Int{
        return when(this.level){
            listLevel[0] -> -1
            listLevel[1] -> 10
            listLevel[2] -> 5
            else -> -2
        }
    }

    fun isContinue(): Boolean{
        return when(this.level){
            listLevel[0] -> this.countOfRounds < this.numberOfRounds[0]
            listLevel[1] -> this.countOfRounds < this.numberOfRounds[1]
            listLevel[2] -> this.countOfRounds < this.numberOfRounds[2]
            else -> false
        }

    }
}
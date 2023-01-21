package com.example.gunbakergame

import kotlin.random.Random

class RandomNumber (level: String){
    val level : String = level
    var size : Int = 0
    var listRandomNumber: ArrayList<Int> = arrayListOf()

    init{
        when(this.level){
            "easy" -> {
                this.size = 10
                this.originalRandom()
            }
            "medium" -> {
                this.size = 25
                this.shuffleRandom()
            }
            "hard" -> {
                this.size = 50
                this.hardShuffleRandom()
            }
            else -> this.size = 0
        }
    }
    fun startRandom(): ArrayList<Int>{
        return when(this.level){
            "easy" -> this.originalRandom()
            "medium" -> this.shuffleRandom()
            "hard" -> this.hardShuffleRandom()
            else -> this.listRandomNumber
        }

    }
    fun originalRandom(): ArrayList<Int>{
        this.reset()
        for (i in 0..this.size-1){
            this.listRandomNumber.add(Random.nextInt(0, 3))
        }
        return this.listRandomNumber
    }
    fun shuffleRandom():ArrayList<Int>{
        this.reset()
        this.originalRandom()
        this.listRandomNumber.shuffle()
        return this.listRandomNumber
    }
    fun isRepeat(): Boolean{
        for (i in 0..this.size-3){
            if(this.listRandomNumber[i]==listRandomNumber[i+1] && listRandomNumber[i+1]==listRandomNumber[i+2]){
                return true
            }
        }
        return false
    }
    fun reset(){
        this.listRandomNumber = arrayListOf<Int>()
    }
    fun hardShuffleRandom(): ArrayList<Int>{
        this.shuffleRandom()
        if (isRepeat()){
            this.shuffleRandom()
            this.hardShuffleRandom()
        }
        return this.listRandomNumber
    }
}
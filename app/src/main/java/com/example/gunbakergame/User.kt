package com.example.gunbakergame

class User(nameParams: String, passwordParams: String, bestScoreParams: Int){

    private var name: String = nameParams
    private var password: String = passwordParams
    private var bestScore: Int = bestScoreParams



    public fun getName():String{
        return this.name
    }
    public fun getPassword():String{
        return this.password
    }
    public fun getBestScore():Int{
        return this.bestScore
    }
    public fun setBestScore(score : Int){
        this.bestScore = score
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
    override fun equals(other: Any?):Boolean = when(other){
        is User -> name == other.name
        else -> false
    }

}
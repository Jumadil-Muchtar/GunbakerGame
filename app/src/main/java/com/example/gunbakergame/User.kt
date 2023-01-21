package com.example.gunbakergame

class User(nameParams: String, passwordParams: String, bestScoreParams: ArrayList<Int>){

    private var name: String = nameParams
    private var password: String = passwordParams
    private var bestScore: ArrayList<Int> = bestScoreParams

    public fun getName():String{
        return this.name
    }
    public fun getPassword():String{
        return this.password
    }
    public fun getBestScore(index: Int):Int{
        return this.bestScore[index]
    }
    public fun getBestScore(level :String):Int{
        when(level){
            "easy"-> return this.bestScore[0]
            "medium"-> return this.bestScore[1]
            "hard"-> return this.bestScore[2]
            else -> return 0
        }
    }
    public fun setBestScore(index: Int, score : Int){
        this.bestScore[index] = score
    }
    override fun hashCode(): Int {
        return name.hashCode()
    }
    override fun equals(other: Any?):Boolean = when(other){
        is User -> name == other.name
        else -> false
    }

}
package com.example.gunbakergame

class Authority(objParams: User){
    private var objUser: User = objParams
    public var indexOfUser: Int = 0


    public fun login(nameOfUser: ArrayList<String>, passwordOfUser: ArrayList<String>) : Boolean{
        var count = 0
        if(nameOfUser.contains(this.objUser.getName())){
            for (i in 0 .. nameOfUser.size - 1){
                if (nameOfUser[i].equals(this.objUser.getName()) && passwordOfUser[i].equals(this.objUser.getPassword())){
                    this.indexOfUser = count
                    return true
                }
                count++
            }
        }
        return false
    }
    public fun isRegisteredIn( nameOfUser: ArrayList<String>): Boolean{
        if(nameOfUser.contains(this.objUser.getName())){
            return true
        }
        return false
    }
}
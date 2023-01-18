package com.example.gunbakergame

class Authority(objParams: User){
    private var objUser: User = objParams
    public var nameOfUser: String = objUser.getName()
    public var passwordOfUser: String = objUser.getPassword()
    public var bestScoreOfUser: Int = objUser.getBestScore()
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
//    public fun getAccount(): User{
//        return this.obj
//    }
//    public fun register(objUser: User, users: MutableSet<User>): Boolean{
//        if (users.contains(objUser)){
//            return false
//        }else{
//            users.add(objUser)
//            return true
//        }
//    }
//    public fun isAccountLogged(objUser: User): Boolean{
//        if(objUser.getName().equals(this.obj.getName())){
//            return true
//        }
//        return false
//    }
}
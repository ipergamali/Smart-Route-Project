package com.ioannapergamali.movewise.model

class Driver(
        id : String = "" ,
        name : String = "" ,
        email : String = "" ,
        surname : String = "" ,
        address : UserAddress = UserAddress() ,
        phoneNum : String = "" ,
        username : String = "" ,
        password : String = "" ,
        role : String = "Driver"
) : User(id , name , email , surname , address , phoneNum , username , password , role)
{

    override fun display()
    {
        println("Driver Info:")
        super.display()
    }
}

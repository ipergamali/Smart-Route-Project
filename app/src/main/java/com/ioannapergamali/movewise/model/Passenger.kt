package com.ioannapergamali.movewise.model

class Passenger(
        id : String = "" ,
        name : String = "" ,
        email : String = "" ,
        surname : String = "" ,
        address : UserAddress = UserAddress() ,
        phoneNum : String = "" ,
        username : String = "" ,
        password : String = "" ,
        role : String = "Passenger"
) : User(id , name , email , surname , address , phoneNum , username , password , role)
{

    override fun display()
    {
        println("Passenger Info:")
        super.display()
    }
}

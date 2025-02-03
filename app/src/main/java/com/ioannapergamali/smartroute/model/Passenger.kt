package com.ioannapergamali.smartroute.model

class Passenger(
        id : String = "" ,
        name : String = "" ,
        email : String = "" ,
        surname : String = "" ,
        address : UserAddress = UserAddress() ,
        phoneNum : String = "" ,
        username : String = "" ,
        password : String = ""
) : User(id , name , email , surname , address , phoneNum , username , password , Role.PASSENGER)
{

    override fun display() : String
    {
        return "Passenger Details: " + super.display()
    }
}

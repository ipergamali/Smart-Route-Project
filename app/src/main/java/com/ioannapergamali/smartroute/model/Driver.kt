package com.ioannapergamali.smartroute.model

class Driver(
        id : String = "" ,
        name : String = "" ,
        email : String = "" ,
        surname : String = "" ,
        address : UserAddress = UserAddress() ,
        phoneNum : String = "" ,
        username : String = "" ,
        password : String = ""
) : User(id , name , email , surname , address , phoneNum , username , password , Role.DRIVER)
{

    override fun display() : String
    {
        return "Driver Details: " + super.display()
    }
}

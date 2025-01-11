package com.ioannapergamali.smartroute.model

class Admin(
        id : String = "" ,
        name : String = "" ,
        email : String = "" ,
        surname : String = "" ,
        address : UserAddress = UserAddress() ,
        phoneNum : String = "" ,
        username : String = "" ,
        password : String = "" ,
        role : String = "Admin"
) : User(id , name , email , surname , address , phoneNum , username , password , role)
{

    override fun display()
    {
        println("Admin Info:")
        super.display()
    }
}

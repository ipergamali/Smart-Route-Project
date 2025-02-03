package com.ioannapergamali.movewise.model

open class User(
        private var id : String = "" ,
        private var name : String = "" ,
        private var email : String = "" ,
        private var surname : String = "" ,
        private var address : UserAddress = UserAddress() ,
        private var phoneNum : String = "" ,
        private var username : String = "" ,
        private var password : String = "" ,
        private var role: String = ""
)
{
    // Getters
    fun getId() = id
    fun getName() = name
    fun getEmail() = email
    fun getSurname() = surname
    fun getAddress() = address
    fun getPhoneNum() = phoneNum
    fun getUsername() = username
    fun getPassword() = password
    fun getRole() = role

    // Setters
    fun setId(value : String)
    {
        id = value
    }

    fun setName(value : String)
    {
        name = value
    }

    fun setEmail(value : String)
    {
        email = value
    }

    fun setSurname(value : String)
    {
        surname = value
    }

    fun setAddress(value : UserAddress)
    {
        address = value
    }

    fun setPhoneNum(value : String)
    {
        phoneNum = value
    }

    fun setUsername(value : String)
    {
        username = value
    }

    fun setPassword(value : String)
    {
        password = value
    }

    fun setRole(value: String)
    {
        role = value
    }

    // Display user details
    open fun display()
    {
        println(
            "ID: $id, Name: $name, Surname: $surname, Email: $email, Role: $role, " +
                    "Address: ${address.getCity()} ${address.getStreetName()} ${address.getStreetNum()}, " +
                    "Phone: $phoneNum, Username: $username"
        )
    }
}

package com.ioannapergamali.smartroute.model

class UserAddress(
        private var city : String = "" ,
        private var streetName : String = "" ,
        private var streetNum : Int = 0 ,
        private var postalCode : Int = 0
)
{
    // Getters
    fun getCity() = city
    fun getStreetName() = streetName
    fun getStreetNum() = streetNum
    fun getPostalCode() = postalCode

    // Setters
    fun setCity(value : String)
    {
        city = value
    }

    fun setStreetName(value : String)
    {
        streetName = value
    }

    fun setStreetNum(value : Int)
    {
        streetNum = value
    }

    fun setPostalCode(value : Int)
    {
        postalCode = value
    }

    // Display details
    fun display() : String
    {
        return "$streetName $streetNum, $city, $postalCode"
    }
}

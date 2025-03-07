package com.ioannapergamali.smartroute.model


data class UserAddress(
        private var city : String = "" ,
        private var streetName : String = "" ,
        private var streetNum : Int = 0 ,
        private var postalCode : Int = 0
)
{
    fun getCity() = city
    fun getStreetName() = streetName
    fun getStreetNum() = streetNum
    fun getPostalCode() = postalCode

    fun display(): String {
        return "$streetName $streetNum, $city"
    }
}
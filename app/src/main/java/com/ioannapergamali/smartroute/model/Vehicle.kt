package com.ioannapergamali.smartroute.model

open class Vehicle(
        val id : String ,
        val description : String ,
        val username : String ,
        val type : String ,
        val seat : Int ,
        val ownership : Boolean
)
{
    open fun display()
    {
        println("ID: $id, Description: $description, Username: $username, Type: $type, Seats: $seat, Ownership: ${if (ownership) "Owner" else "Driver"}")
    }
}

class Car(
        id : String ,
        description : String ,
        username : String ,
        seat : Int ,
        ownership : Boolean
) :
    Vehicle(id , description , username , "Car" , seat , ownership)

class Taxi(id : String , description : String , username : String) :
    Vehicle(id , description , username , "Taxi" , 4 , false)

// Προσθέστε τις υπόλοιπες κλάσεις όπως `Bigbus`, `Smallbus`, `Bicycle`, `Motorbike`.

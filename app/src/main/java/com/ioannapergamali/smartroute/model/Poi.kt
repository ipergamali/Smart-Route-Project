package com.ioannapergamali.smartroute.model

data class Poi(
        val id : Int ,
        val description : String
)
{
    fun print()
    {
        println("ID: $id, Description: $description")
    }
}

data class Route(
        val start : String ,
        val end : String ,
        val cost : Double
)
{
    fun print()
    {
        println("Route: $start -> $end, Cost: $cost")
    }
}

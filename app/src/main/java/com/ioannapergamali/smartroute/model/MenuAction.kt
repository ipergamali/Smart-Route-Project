package com.ioannapergamali.smartroute.model

data class MenuAction(
        val id : Int ,
        val description : String ,
        val action : () -> Unit
)

class Menu(private val role : Role)
{

    private val actions = mutableListOf<MenuAction>()

    init
    {
        when (role)
        {
            Role.PASSENGER -> loadPassengerActions()
            Role.DRIVER    ->
            {
                loadPassengerActions()
                loadDriverActions()
            }

            Role.ADMIN     ->
            {
                loadPassengerActions()
                loadDriverActions()
                loadAdminActions()
            }
        }
    }

    private fun loadPassengerActions()
    {
        actions.add(
                MenuAction(
                        0 ,
                        "Manage Preferred Transport"
                ) { println("Managing transport...") })
        actions.add(
                MenuAction(
                        1 ,
                        "Search and Book Transport"
                ) { println("Searching transport...") })
        actions.add(MenuAction(2 , "View Travel History") { println("Viewing history...") })
        actions.add(MenuAction(3 , "Rate Completed Travels") { println("Rating travels...") })
        actions.add(MenuAction(4 , "Sign Out") { println("Signing out...") })
    }

    private fun loadDriverActions()
    {
        actions.add(MenuAction(5 , "Declare Availability") { println("Declaring availability...") })
        actions.add(MenuAction(6 , "Register Vehicle") { println("Registering vehicle...") })
    }

    private fun loadAdminActions()
    {
        actions.add(MenuAction(7 , "Initialize System") { println("Initializing system...") })
        actions.add(MenuAction(8 , "Create User Account") { println("Creating account...") })
        actions.add(MenuAction(9 , "Promote/Demote User") { println("Updating user role...") })
        actions.add(MenuAction(10 , "Set Points of Interest") { println("Setting points...") })
        actions.add(MenuAction(11 , "View Statistics") { println("Viewing statistics...") })
    }

    fun getActions() : List<MenuAction> = actions
}

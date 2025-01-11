package com.ioannapergamali.smartroute.model

data class MenuAction(
        private var _id : Int ,
        private var _description : String ,
        private var _action : () -> Unit
)
{
    var id : Int
        get() = _id
        set(value)
        {
            _id = value
        }

    var description : String
        get() = _description
        set(value)
        {
            _description = value
        }

    var action : () -> Unit
        get() = _action
        set(value)
        {
            _action = value
        }
}

class Menu(private var _role : Role)
{
    private val _actions = mutableListOf<MenuAction>()

    var role : Role
        get() = _role
        set(value)
        {
            _role = value
            _actions.clear()
            when (value)
            {
                Role.PASSENGER -> loadPassengerActions()
                Role.DRIVER    -> loadDriverActions()
                Role.ADMIN     -> loadAdminActions()
            }
        }

    val actions : List<MenuAction>
        get() = _actions

    init
    {
        when (_role)
        {
            Role.PASSENGER -> loadPassengerActions()
            Role.DRIVER    -> loadDriverActions()
            Role.ADMIN     -> loadAdminActions()
        }
    }

    private fun loadPassengerActions()
    {
        _actions.add(MenuAction(0 , "Sign out") { println("Signing out...") })
        _actions.add(
                MenuAction(
                        1 ,
                        "Manage Favorite Means of Transport"
                ) { println("Managing Transport...") })
    }

    private fun loadDriverActions()
    {
        _actions.add(
                MenuAction(
                        0 ,
                        "Announce Availability"
                ) { println("Announcing availability...") })
        _actions.add(MenuAction(1 , "Register Vehicle") { println("Registering Vehicle...") })
    }

    private fun loadAdminActions()
    {
        _actions.add(MenuAction(0 , "Initialize System") { println("Initializing System...") })
        _actions.add(MenuAction(1 , "View Reports") { println("Viewing Reports...") })
    }

    fun displayMenu()
    {
        _actions.forEach { println("${it.id}. ${it.description}") }
    }

    fun executeAction(id : Int)
    {
        _actions.find { it.id == id }?.action?.invoke()
        ?: println("Invalid Action Selected!")
    }
}

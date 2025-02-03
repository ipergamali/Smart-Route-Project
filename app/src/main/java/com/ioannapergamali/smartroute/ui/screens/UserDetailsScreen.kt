package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ioannapergamali.smartroute.model.User

@Composable
fun UserDetailsScreen(user : User)
{
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
                text = "User Details" ,
                modifier = Modifier.fillMaxWidth() ,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )
        Text(text = "ID: ${user.getId()}" , modifier = Modifier.padding(vertical = 4.dp))
        Text(
                text = "Name: ${user.getName()} ${user.getSurname()}" ,
                modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(text = "Email: ${user.getEmail()}" , modifier = Modifier.padding(vertical = 4.dp))
        Text(text = "Role: ${user.getRole()}" , modifier = Modifier.padding(vertical = 4.dp))
        Text(
                text = "Address: ${user.getAddress().display()}" ,
                modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(text = "Phone: ${user.getPhoneNum()}" , modifier = Modifier.padding(vertical = 4.dp))
        Text(
                text = "Username: ${user.getUsername()}" ,
                modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

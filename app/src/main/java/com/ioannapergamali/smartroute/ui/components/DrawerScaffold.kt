package com.ioannapergamali.smartroute.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScaffold(
        title : String ,
        onSettingsClick : () -> Unit ,
        onLogoutClick : () -> Unit ,
        content : @Composable (paddingValues : PaddingValues) -> Unit
)
{
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
            drawerState = drawerState ,
            drawerContent = {
                DrawerContent(
                        onSettingsClick = {
                            onSettingsClick()
                            coroutineScope.launch { drawerState.close() }
                        } ,
                        onLogoutClick = {
                            onLogoutClick()
                            coroutineScope.launch { drawerState.close() }
                        }
                )
            }
    ) {
        Scaffold(
                topBar = {
                    TopAppBar(
                            title = { Text(title) } ,
                            navigationIcon = {
                                IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                    Icon(Icons.Default.Menu , contentDescription = "Menu")
                                }
                            }
                    )
                } ,
                content = content
        )
    }
}

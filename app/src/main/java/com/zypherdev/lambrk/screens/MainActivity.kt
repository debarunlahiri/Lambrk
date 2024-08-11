package com.zypherdev.lambrk.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zypherdev.lambrk.models.BottomNavItem

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { NavigationBar(navController = navController) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigation(navController = navController)
            }
        }
    }

    @Composable
    fun NavigationBar(navController: NavController) {  // Corrected parameter type
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Create,
            BottomNavItem.Flickr,
            BottomNavItem.Profile
        )

        androidx.compose.material3.NavigationBar(
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(text = item.label) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { startDestination ->
                                popUpTo(startDestination) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun AppNavigation(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.route) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Search.route) { SearchScreen() }
            composable(BottomNavItem.Create.route) { CreateScreen() }
            composable(BottomNavItem.Flickr.route) { FlickrScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }



    @Composable
    fun SearchScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Search Screen")
        }
    }

    @Composable
    fun CreateScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Create Screen")
        }
    }

    @Composable
    fun FlickrScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Flickr Screen")
        }
    }

    @Composable
    fun ProfileScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Profile Screen")
        }
    }
}

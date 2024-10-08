package com.zypherdev.lambrk.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Create : BottomNavItem("create", Icons.Default.AddCircle, "Create")
    object Flickr : BottomNavItem("flickr", Icons.Default.PlayArrow, "Flickr")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
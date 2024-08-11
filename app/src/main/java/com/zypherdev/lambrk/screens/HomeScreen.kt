package com.zypherdev.lambrk.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { HomeScreenContent(navController = navController) }
                composable("chat") { ChatScreenContent(navController) }
            }
        }
    }
    @Composable
    public fun HomeScreenContent(navController: NavHostController) {
        Scaffold(

        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                TopSection(navController)
                StorySection()
                ContentSection()
            }
        }
    }

    @Composable
    fun TopSection(navController: NavHostController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Lambrk", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Row {
                IconButton(onClick = {
                    navController.navigate("add")
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = {
                    navController.navigate("like")
                }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = {
                    navController.navigate("chat")
                }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send Message"
                    )
                }
            }
        }
    }



    @Composable
    fun StorySection() {
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Gray, shape = CircleShape)
                            .border(1.dp, Color.Black, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Story $index", fontSize = 12.sp)
                }
            }
        }
    }

    @Composable
    fun ContentSection() {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(10) { index ->
                PostItem(index)
            }
        }
    }

    @Composable
    fun PostItem(index: Int) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Post $index", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "This is the content for post $index.")
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen()
    }

}
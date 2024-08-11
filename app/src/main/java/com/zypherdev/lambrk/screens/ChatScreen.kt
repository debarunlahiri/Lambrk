package com.zypherdev.lambrk.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("chat") { ChatScreenContent(navController = navController) }
            }
        }
    }
}

@Composable
fun ChatScreenContent(navController: NavHostController) {
    Scaffold(
        topBar = { ChatAppBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SearchBar()
            }

            item {
                OnlineSection()
            }

            items(10) { index ->
                MessageItem(
                    name = "User $index",
                    message = "messaged you · ${index * 10}m",
                    time = "Active ${index}h ago"
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "mota_rakshas", fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack() // Handle back navigation
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle edit action */ }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    )
}


@Composable
fun SearchBar() {
    var textState = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .background(Color(0xFFF2F2F2), shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            decorationBox = { innerTextField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    Spacer(modifier = Modifier.width(8.dp))
                    if (textState.value.text.isEmpty()) {
                        Text(text = "Search", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun OnlineSection() {
    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        contentPadding = PaddingValues()
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
                        .border(2.dp, Color.Green, shape = CircleShape)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "User $index", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MessageList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

    }
}

@Composable
fun MessageItem(name: String, message: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.Gray, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message, color = Color.Gray)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Camera")
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val navController = rememberNavController()
    ChatScreenContent(navController)
}

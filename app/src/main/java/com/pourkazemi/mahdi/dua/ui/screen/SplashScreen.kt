package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

//#Todo customize and put some icon and text to introduce me and Daralvelaye
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    //onContinueClicked: () -> Unit
) {
    // State to control the visibility of the splash screen
    var showSplashScreen by remember { mutableStateOf(true) }

    // Effect to hide the splash screen after 1 second
    LaunchedEffect(Unit) {
        delay(1500) // Wait for 1 second
        showSplashScreen = false
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = {}
            ) {
                Text("Continue")
            }
        }

        // Transparent splash screen overlay
        if (showSplashScreen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.7f)), // Semi-transparent black
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Splash Screen",
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingPreview() {
    SplashScreen() /*{
        println("Dua SplashScreen OnBoardingPreview Clicked")
    }*/
}
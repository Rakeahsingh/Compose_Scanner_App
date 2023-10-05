package com.example.scannerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scannerapp.screens.MainScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = ScannerApp.MainScreen.name){
        composable(ScannerApp.MainScreen.name){
            MainScreen(navController = navController)
        }
    }

}
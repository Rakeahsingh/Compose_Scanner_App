package com.example.scannerapp.navigation

enum class ScannerApp {
    MainScreen;

    companion object{
        fun fromRoute(route: String?): ScannerApp{
           return when(route?.substringBefore("/")){
                MainScreen.name -> MainScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("Route $route is not Exist")
            }
        }
    }
}
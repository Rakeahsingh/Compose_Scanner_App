package com.example.scannerapp.domain

import kotlinx.coroutines.flow.Flow

interface ScanRepo {

    fun StartScan(): Flow<String?>

}
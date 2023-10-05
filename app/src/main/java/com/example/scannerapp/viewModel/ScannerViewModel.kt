package com.example.scannerapp.viewModel

import android.annotation.SuppressLint
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scannerapp.Resources
import com.example.scannerapp.domain.ScanRepo
import com.example.scannerapp.model.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val repo: ScanRepo,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
): ViewModel(){

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    @SuppressLint("MissingPermission")
    fun getNotification() {
        notificationManager.notify(1,notificationBuilder.build())
    }

    fun startScanning(){
        viewModelScope.launch {
           repo.StartScan().collect{ data ->
               if (!data.isNullOrBlank()){
                   _state.value = state.value.copy(
                       Details = data
                   )
               }
           }
        }
    }

}
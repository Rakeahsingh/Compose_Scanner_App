package com.example.scannerapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scannerapp.viewModel.ScannerViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: ScannerViewModel = hiltViewModel()

) {

    val state = viewModel.state.collectAsState()


    var isChecked by remember { mutableStateOf(false) }

   if (isChecked) {
       DialogBox(
           title ="Start Scanning",
           text ="Are you to Scann any QR Code",
           onDismiss = { isChecked = false},
           onCirfirm = {
               viewModel.getNotification()
               viewModel.startScanning()
               isChecked = false
           })
   }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        Text(modifier = Modifier.padding(10.dp),
            text = state.value.Details,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)

        Button(onClick = {
            isChecked = true
        })
        {
         Text(text = "Start Scanning")
        }

    }


}


@Composable
fun DialogBox(
    title: String,
    text: String,
    onDismiss: ()-> Unit,
    onCirfirm: ()-> Unit
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription ="icon" )
        },
        title = {
               Text(text = title)
        },
       text = {
              Text(text =text)
       } ,
        onDismissRequest = { onDismiss() },
        confirmButton = {
           TextButton(onClick =  onCirfirm) {
               Text(text = "Confirm")
           } },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss")

            }
        })
}
package com.example.scannerapp.repository

import com.example.scannerapp.domain.ScanRepo
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScanRepo @Inject constructor(
    private val scanner: GmsBarcodeScanner
): ScanRepo {


    override fun StartScan() : Flow<String?> {
        return callbackFlow {
          scanner.startScan()
               .addOnSuccessListener {barcode->
                   launch {
                       send(getDetails(barcode))
                   }
               }
               .addOnFailureListener {
                   it.printStackTrace()
               }

            awaitClose {  }
        }
    }

    private fun getDetails(barcode: Barcode): String{

        return when(barcode.valueType){
            Barcode.TYPE_WIFI -> {
                val ssid = barcode.wifi!!.ssid
                val password = barcode.wifi!!.password
                val type = barcode.wifi!!.encryptionType
                "ssid: $ssid, password: $password, type: $type"
            }
            Barcode.TYPE_URL -> {
                val title = barcode.url!!.title
                val url = barcode.url!!.url
                "title: $title, url: $url"
            }
            Barcode.TYPE_PRODUCT -> {
                "product: ${barcode.displayValue}"
            }
            Barcode.TYPE_EMAIL -> {
                "email: ${barcode.email}"
            }
            Barcode.TYPE_CONTACT_INFO -> {
                "contact: ${barcode.contactInfo}"
            }
            Barcode.TYPE_PHONE -> {
                "phone: ${barcode.phone}"
            }
            Barcode.TYPE_CALENDAR_EVENT -> {
                "calender: ${barcode.calendarEvent}"
            }
            Barcode.TYPE_GEO -> {
                "Geo: ${barcode.geoPoint}"
            }
            Barcode.TYPE_ISBN -> {
                "Isbn: ${barcode.displayValue}"
            }
            Barcode.TYPE_DRIVER_LICENSE -> {
                "Driving: ${barcode.driverLicense}"
            }
            Barcode.TYPE_SMS -> {
                "sms: ${barcode.sms}"
            }
            Barcode.TYPE_TEXT -> {
                "text: ${barcode.rawValue}"
            }
            Barcode.TYPE_UNKNOWN -> {
                "unknown: ${barcode.rawValue}"
            }
            else -> {
                barcode.rawValue ?: "Couldn't Determined"
            }
        }

    }


}


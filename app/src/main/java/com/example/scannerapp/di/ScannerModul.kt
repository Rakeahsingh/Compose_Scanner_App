package com.example.scannerapp.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.scannerapp.R
import com.example.scannerapp.domain.ScanRepo
import com.example.scannerapp.repository.MainScanRepo
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScannerModul {

    @Provides
    @Singleton
    fun provideOptions():GmsBarcodeScannerOptions{
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_ALL_FORMATS,
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC,
                Barcode.FORMAT_DATA_MATRIX,
                Barcode.FORMAT_PDF417)
            .enableAutoZoom()
            .build()
    }

    @Provides
    @Singleton
    fun provideScanner(
        @ApplicationContext context: Context,
        options: GmsBarcodeScannerOptions
    ):GmsBarcodeScanner{
        return GmsBarcodeScanning.getClient(context, options)
    }

    @Provides
    @Singleton
    fun provideRepo(mainScanRepo: MainScanRepo):ScanRepo{
        return mainScanRepo
    }

    @Provides
    @Singleton
    fun provideNotificationBuilder(
        @ApplicationContext context: Context): NotificationCompat.Builder{
        return NotificationCompat.Builder(context,"Scanner_Notification")
            .setContentTitle("Start Scanning")
            .setContentText("Are you Ready to Scan any QR CODE")
            .setSmallIcon(R.drawable.img)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat{
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val channel = NotificationChannel(
                "Scanner_Notification",
                "Scanner Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }


}
package com.example.idusassignment

import android.app.Application
import com.example.idusassignment.di.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules (
                listOf(
                    httpLoggingModule,
                    okHttpClientModule,
                    weatherApiModule,
                    weatherRepositoryModule,
                    mainViewModelModule
                )
            )
        }
    }
}
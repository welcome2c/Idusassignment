package com.example.idusassignment.di

import com.example.idusassignment.data.WeatherApi
import com.example.idusassignment.data.repository.WeatherRepository
import com.example.idusassignment.data.repository.WeatherRepositoryImpl
import com.example.idusassignment.ui.main.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIME_OUT = 10L
val httpLoggingModule = module {
    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

val okHttpClientModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .build()
    }
}

val weatherApiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
            .create(WeatherApi::class.java)
    }
}

val weatherRepositoryModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
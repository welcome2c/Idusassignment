package com.example.idusassignment

import com.example.idusassignment.data.WeatherApi
import com.example.idusassignment.data.model.WeatherPresentation
import com.example.idusassignment.data.repository.WeatherRepositoryImpl
import com.example.idusassignment.di.TIME_OUT
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @ExperimentalTime
    @Test
    fun addition_isCorrect() {
        val weatherRepository = WeatherRepositoryImpl(
            Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                        )
                        .build()
                )
                .build()
                .create(WeatherApi::class.java)
        )

        runBlocking {
            var list: MutableList<WeatherPresentation.WeatherResult> = mutableListOf()
            var list2: MutableList<WeatherPresentation> = mutableListOf()

            val measure =   measureTime {
                weatherRepository
                    .getCity()
                    .flatMapConcat {
                        //응답인 Flow 가 완료되어야 다음 단계를 순차적으로 진행한다.
                        it.asFlow()
                    }

                    .flatMapMerge {
                        // 응답인 Flow의 완료 여부와 상관없이 다음 단계를 동시에 진행한다.
//                        println("----------------------- ${it.title}")
                        weatherRepository.getWeather(it.woeid)
                    }
                        .onCompletion {
                            for (model in list2) {
                                println(model.title)
                                println(model.list.size)
                            }
                        }
                    .collect { it ->
                        list = mutableListOf()

                        val title = it.title
                        it.consolidated_weather.asFlow().take(2).collect {
                            list.add(WeatherPresentation.WeatherResult(it.weather_state_name, it.weather_state_abbr, it.the_temp, it.humidity))
                        }
                        list2.add(WeatherPresentation(title, list))
                    }
            }

            println(measure)
        }
    }
}
package uz.targetsoftwaredevelopment.myapplication.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.targetsoftwaredevelopment.myapplication.BuildConfig.LOGGING
import uz.targetsoftwaredevelopment.myapplication.app.App
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import uz.targetsoftwaredevelopment.myapplication.data.local.SafeStorage
import uz.targetsoftwaredevelopment.myapplication.utils.timber

object ApiClient {
    private val safeStorage = SafeStorage()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(safeStorage.base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addLogging()
            .addInterceptor(tokenInterceptor())
            .build()
    }
}

fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            timber(message, "HTTP")
        }
    }
    if (LOGGING) {
        addInterceptor(ChuckInterceptor(App.instance))
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}

fun tokenInterceptor() = Interceptor { chain ->
    val pref = LocalStorage()

    val chainRequest = chain.request()
    val newRequest =
        chainRequest.newBuilder().removeHeader("Authorization")
            .addHeader("Authorization", pref.token).build()

    val response = chain.proceed(newRequest)
    response
}

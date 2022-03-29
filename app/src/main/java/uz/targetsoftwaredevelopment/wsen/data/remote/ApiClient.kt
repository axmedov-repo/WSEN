package uz.targetsoftwaredevelopment.wsen.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.targetsoftwaredevelopment.wsen.BuildConfig.LOGGING
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.local.LocalStorage
import uz.targetsoftwaredevelopment.wsen.data.local.SafeStorage
import uz.targetsoftwaredevelopment.wsen.utils.timber
import java.net.URL

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

/*
fun tokenInterceptor() = Interceptor { chain ->
    val pref = LocalStorage()
    val safeStorage = SafeStorage()

    val chainRequest = chain.request()
    val newRequest =
        if (chainRequest.url.toUrl() == URL("${safeStorage.base_url}client/logout/")) {
            chainRequest.newBuilder().removeHeader("Authorization").build()
        } else {
            chainRequest.newBuilder().removeHeader("Authorization")
                .addHeader("Authorization", pref.token).build()
        }
    val response = chain.proceed(newRequest)
    response
}
*/

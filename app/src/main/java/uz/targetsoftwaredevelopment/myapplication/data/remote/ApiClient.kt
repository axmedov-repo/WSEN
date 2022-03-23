package uz.targetsoftwaredevelopment.myapplication.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.targetsoftwaredevelopment.myapplication.BuildConfig.BASE_URL
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor())
            .build()
    }
}

fun tokenInterceptor() = Interceptor { chain ->
    val pref = LocalStorage()

    val chainRequest = chain.request()
    val newRequest =
        chainRequest.newBuilder().removeHeader("Authorization").addHeader("Authorization", pref.token).build()

    val response = chain.proceed(newRequest)
    response
}

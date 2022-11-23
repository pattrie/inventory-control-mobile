package br.com.fiap.inventorycontrol.helpers

import br.com.fiap.inventorycontrol.services.LoginService
import br.com.fiap.inventorycontrol.services.ProductService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8080/"

var client = OkHttpClient.Builder().build()

var gson = GsonBuilder()
    .setLenient()
    .create()

fun getRetrofitFactory() = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun retrofitLoginService(): LoginService =
    getRetrofitFactory()
        .create(LoginService::class.java)

fun retrofitProductService(): ProductService =
    getRetrofitFactory()
        .create(ProductService::class.java)
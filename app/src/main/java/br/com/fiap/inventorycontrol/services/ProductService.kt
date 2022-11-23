package br.com.fiap.inventorycontrol.services

import br.com.fiap.inventorycontrol.models.ProductResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductService {

    @GET("/api/v1/inventory/all")
    fun getProducts(
        @Header("Authorization") authorization: String
    ): Call<List<ProductResponseModel>>
}
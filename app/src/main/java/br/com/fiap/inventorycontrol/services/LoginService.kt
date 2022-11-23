package br.com.fiap.inventorycontrol.services

import br.com.fiap.inventorycontrol.models.LoginRequestModel
import br.com.fiap.inventorycontrol.models.LoginResponseModel
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @POST("auth")
    fun access(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>

}
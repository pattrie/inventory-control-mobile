package br.com.fiap.inventorycontrol.models

data class LoginResponseModel(
    var token: String,
    var type: String
)
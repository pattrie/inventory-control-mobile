package br.com.fiap.inventorycontrol.models

data class StorePlaceResponseModel(
    var id: String,
    var address: AddressResponseModel,
    var quantity: Long,
    var createdAt: String
)

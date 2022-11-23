package br.com.fiap.inventorycontrol.models

data class AddressResponseModel(
    var zipCode: String,
    var street: String,
    var number: String,
    var complementary: String,
    var city: String,
    var neighborhood: String,
    var state: String,
    var country: String
)

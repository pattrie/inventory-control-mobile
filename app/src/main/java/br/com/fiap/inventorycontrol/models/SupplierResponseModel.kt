package br.com.fiap.inventorycontrol.models

data class SupplierResponseModel(
    var id: String,
    var name: String,
    var address: AddressResponseModel,
    var cnpj: String,
    var phone: String,
    var email: String
)

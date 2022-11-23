package br.com.fiap.inventorycontrol.models

import java.time.LocalDateTime

data class ProductResponseModel(
    var id: String,

    var name: String,

    var description: String,

    var image: String,

    var color: String,

    var quantity: Long,

    var unitaryValue: Number,

    var category: CategoryResponseModel,

    var supplier: SupplierResponseModel,

    var storagePlaces: List<StorePlaceResponseModel>,

    var createdAt: String
)

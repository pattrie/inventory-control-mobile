package br.com.fiap.inventorycontrol.models

var productList = mutableListOf<Product>()

const val PRODUCT_EXTRA = "productExtra"

class Product(
    var cover: Int?,
    var name: String,
    var description: String?,
    var color: String?,
    var location: String?,
    var quantity: Int?,
    var size: Int? = productList.size
) {
    override fun toString(): String {
        return "Product(cover=$cover, name='$name', description=$description, color=$color, location=$location, quantity=$quantity, size=$size)"
    }
}
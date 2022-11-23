package br.com.fiap.inventorycontrol.helpers

import br.com.fiap.inventorycontrol.models.Product

interface ProductClickListener {
    fun onClick(product: Product)
}
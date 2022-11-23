package br.com.fiap.inventorycontrol

import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.inventorycontrol.databinding.CardCellBinding
import br.com.fiap.inventorycontrol.helpers.ProductClickListener
import br.com.fiap.inventorycontrol.models.Product
import java.security.PrivateKey

class CardViewHolder(
    private val cardCellBinding : CardCellBinding,
    private val clickListener: ProductClickListener
    ) : RecyclerView.ViewHolder(cardCellBinding.root)
{

    fun bindProduct(product: Product) {
        product.cover?.let { cardCellBinding.cover.setImageResource(it) }
        cardCellBinding.textProductName.text = product.name

        cardCellBinding.cardView.setOnClickListener {
            clickListener.onClick(product)
        }
    }
}
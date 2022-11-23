package br.com.fiap.inventorycontrol.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.inventorycontrol.CardViewHolder
import br.com.fiap.inventorycontrol.databinding.CardCellBinding
import br.com.fiap.inventorycontrol.helpers.ProductClickListener
import br.com.fiap.inventorycontrol.models.Product

class CardAdapter(
    private val products: List<Product>,
    private val clickListener: ProductClickListener
) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    override fun getItemCount(): Int = products.size
}
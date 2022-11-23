package br.com.fiap.inventorycontrol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.inventorycontrol.R
import br.com.fiap.inventorycontrol.models.Product

class ProductListAdapter(
    private val context: Context,
    private val products: List<Product>
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.name)
            name.text = product.name

            val description = itemView.findViewById<TextView>(R.id.descricao)
            description.text = product.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size


}

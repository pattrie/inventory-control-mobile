package br.com.fiap.inventorycontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inventorycontrol.databinding.ActivityDetailBinding
import br.com.fiap.inventorycontrol.models.PRODUCT_EXTRA
import br.com.fiap.inventorycontrol.models.Product
import br.com.fiap.inventorycontrol.models.productList

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra(PRODUCT_EXTRA, -1)
        val product = productFromId(productId)
        if(product != null) {
            product.cover?.let { binding.imageViewPicture.setImageResource(it) }
            binding.editTextProduct.text = product.name
            binding.editTextDescription.text = product.description
            binding.editTextColor.text = product.color
            binding.editTextLocation.text = product.location
            binding.editTextQuantityTotal.text = product.quantity.toString()
            binding.editTextQuantity.text = product.quantity.toString()
        }
    }

    private fun productFromId(productId: Int): Product ? {
        for(product in productList) {
            if (product.size == productId) return product
        }
        return null
    }
}
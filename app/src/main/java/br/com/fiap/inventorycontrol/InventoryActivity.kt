package br.com.fiap.inventorycontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.inventorycontrol.databinding.ActivityInventoryBinding
import br.com.fiap.inventorycontrol.databinding.ActivityMainBinding

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInventoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}
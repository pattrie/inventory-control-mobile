package br.com.fiap.inventorycontrol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fiap.inventorycontrol.adapters.CardAdapter
import br.com.fiap.inventorycontrol.databinding.ActivityProductRecyclerViewLayoutBinding
import br.com.fiap.inventorycontrol.helpers.ProductClickListener
import br.com.fiap.inventorycontrol.helpers.retrofitProductService
import br.com.fiap.inventorycontrol.models.PRODUCT_EXTRA
import br.com.fiap.inventorycontrol.models.Product
import br.com.fiap.inventorycontrol.models.ProductResponseModel
import br.com.fiap.inventorycontrol.models.productList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRecyclerViewActivity : AppCompatActivity(), ProductClickListener {

    private lateinit var binding: ActivityProductRecyclerViewLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRecyclerViewLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productList = populateProducts()

        val productRecyclerViewActivity = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(productList, productRecyclerViewActivity)
        }
    }

    private fun populateProducts(): MutableList<Product> {

        val retrieveIntentData = intent.extras
        val token = retrieveIntentData?.getString("token").toString()

        val call = retrofitProductService().getProducts("Bearer $token".trim())

        var responseBodyOk = ArrayList<Product>()

        call.enqueue(object : Callback<List<ProductResponseModel>> {
            override fun onResponse(
                call: Call<List<ProductResponseModel>>,
                response: Response<List<ProductResponseModel>>
            ) {
                Log.i("code-http-list-products", "CÓDIGO HTTP: ${response.code()}")
                Log.i("response-body", response.body().toString())

                if (response.code() == 403) {
                    Log.i("error-auth", "An error has occurred!")
                    Toast.makeText(
                        this@ProductRecyclerViewActivity,
                        "Ocorreu um erro na autenticação!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (response.code() != 200) {
                    Log.i("error", "An error has occurred!")
                    Toast.makeText(
                        this@ProductRecyclerViewActivity,
                        "Ocorreu um erro!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                println("response-body" + response.body().toString())

                response.body()?.forEach {
                    var address =
                        if (it.storagePlaces.isEmpty()) null else it.storagePlaces[0].address

                    var product = Product(
                        R.drawable.furiosa,
                        it.name,
                        it.description,
                        it.color,
                        String.format(
                            "%s, %s - %s, %s - CEP: %s",
                            address?.street,
                            address?.number,
                            address?.city,
                            address?.state,
                            address?.zipCode
                        ),
                        10
                    )

                    responseBodyOk.add(product)
                }
            }

            override fun onFailure(call: Call<List<ProductResponseModel>>, t: Throwable) {
                Toast.makeText(
                    this@ProductRecyclerViewActivity,
                    String.format("Ocorreu um erro! %s", t.message.toString()),
                    Toast.LENGTH_SHORT
                ).show()

                println(
                    String.format(
                        "Erro :: %s, %s, %s",
                        t.message.toString(),
                        t.cause.toString(),
                        t.stackTrace.toString()
                    )
                )

            }
        })

        Log.i("responseBodyOk", responseBodyOk.toString())

        productList.addAll(responseBodyOk)

        val product1 = Product(
            R.drawable.furiosa,
            "Furiosa",
            "Descrição aaaaaaaaa",
            "verde",
            "Rua da Alegria",
            10
        )

        productList.add(product1)

        val product2 = Product(
            R.drawable.no_image_icon,
            "No image",
            "Descrição2 bbbbbbbbb",
            "cinza",
            "Rua da Pacoca",
            5
        )

        productList.add(product2)
        productList.add(product1)
        productList.add(product2)
        productList.add(product1)

        Log.i("product-list", productList.toString())

        return productList
    }

    override fun onClick(product: Product) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(PRODUCT_EXTRA, product.size)
        startActivity(intent)
    }
}
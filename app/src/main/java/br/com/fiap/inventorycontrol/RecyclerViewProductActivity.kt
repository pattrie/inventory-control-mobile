package br.com.fiap.inventorycontrol

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.inventorycontrol.adapters.ProductListAdapter
import br.com.fiap.inventorycontrol.helpers.retrofitProductService
import br.com.fiap.inventorycontrol.models.Product
import br.com.fiap.inventorycontrol.models.ProductResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_product)

        val recyclerView = findViewById<RecyclerView>(R.id.reclycler_view_product)

        populateProducts()

        recyclerView.adapter = ProductListAdapter(
            this, listOf(
                Product(
                    R.drawable.furiosa,
                    "Nome do Produto1",
                    "Descrição1",
                    "Preto",
                    "Localização1",
                    10,
                    0
                ),
                Product(
                    R.drawable.furiosa,
                    "Nome do Produto2",
                    "Descrição2",
                    "Verde",
                    "Localização2",
                    20,
                    0
                )
            )
        )
    }

    private fun populateProducts() {
        val retrieveIntentData = intent.extras
        val token = retrieveIntentData?.getString("token").toString()

        val call = retrofitProductService().getProducts("Bearer $token".trim())

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
                        this@RecyclerViewProductActivity,
                        "Ocorreu um erro na autenticação!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (response.code() != 200) {
                    Log.i("error", "An error has occurred!")
                    Toast.makeText(
                        this@RecyclerViewProductActivity,
                        "Ocorreu um erro!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                println("response-body" + response.body().toString())

                response.body()?.forEach {
                    var address =
                        if (it.storagePlaces.isEmpty()) null else it.storagePlaces[0].address
                        Product(
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
                }
            }

            override fun onFailure(call: Call<List<ProductResponseModel>>, t: Throwable) {
                Toast.makeText(
                    this@RecyclerViewProductActivity,
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
    }
}
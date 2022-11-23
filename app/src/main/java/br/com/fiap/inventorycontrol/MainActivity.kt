package br.com.fiap.inventorycontrol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inventorycontrol.databinding.ActivityMainBinding
import br.com.fiap.inventorycontrol.helpers.retrofitLoginService
import br.com.fiap.inventorycontrol.models.LoginRequestModel
import br.com.fiap.inventorycontrol.models.LoginResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            autenticar()
        }
    }

    private fun autenticar() {
        val email = binding.edtUser.text.toString()
        val password = binding.edtPassword.text.toString()

        val loginRequestModel = LoginRequestModel(email, password)

        val call = retrofitLoginService().access(loginRequestModel)

        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                Log.i("code-http", "CÓDIGO HTTP: ${response.code()}")
                Log.i("response-body", response.body().toString())

                if (response.code() == 400) {
                    Log.i("warn-user-not-found", "User not found! $email")

                    Toast.makeText(
                        this@MainActivity,
                        "Usuário ou senha incorreto!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (response.code() == 200) {
                    val intent = Intent(this@MainActivity, ProductRecyclerViewActivity::class.java)
                    intent.putExtra("type", response.body()?.type.toString())
                    intent.putExtra("token", response.body()?.token.toString())
                    startActivity(intent)
                    this@MainActivity.finish()
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
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

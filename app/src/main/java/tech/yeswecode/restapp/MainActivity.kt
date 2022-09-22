package tech.yeswecode.restapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import tech.yeswecode.restapp.models.Product
import tech.yeswecode.restapp.utils.Constants
import tech.yeswecode.restapp.utils.HTTPSWebUtilDomi
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val product = Product("New product", 10.0, "A description", 1, ArrayList<String>())
        createProduct(product)

        getProduct(228)

        getProducts()
    }

    private fun createProduct(product: Product) {
        val json = Gson().toJson(product)

        lifecycleScope.launch(Dispatchers.IO) {
            val response = HTTPSWebUtilDomi().POSTRequest("${Constants.BASE_URL}/products", json.toString())
            val jsonObject = JSONObject(response).toString()
            withContext(Dispatchers.Main) {
                // TODO: Show in the detail of the created product
                Log.e("PRODUCTO CREADO", jsonObject)
            }
        }
    }

    private fun getProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val response = HTTPSWebUtilDomi().GETRequest("${Constants.BASE_URL}/products")
            val jsonArray = JSONArray(response).toString()
            withContext(Dispatchers.Main) {
                // TODO: Show in the screen the list of products
                Log.e("LISTA DE PRODUCTOS", jsonArray)
            }
        }
    }

    private fun getProduct(by: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val response = HTTPSWebUtilDomi().GETRequest("${Constants.BASE_URL}/products/${by}")
            val jsonObject = JSONObject(response).toString()
            val product = Gson().fromJson(jsonObject, Product::class.java)
            withContext(Dispatchers.Main) {
                // TODO: Show in the detail of the product
                Log.e("PRODUCTO", jsonObject)
            }
        }
    }
}
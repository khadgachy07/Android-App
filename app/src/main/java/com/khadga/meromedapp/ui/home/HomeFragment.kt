package com.khadga.meromedapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.khadga.meromedapp.R
import com.khadga.meromedapp.adapter.ProductAdapter
import com.khadga.meromedapp.db.ProductDB
import com.khadga.meromedapp.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var rvProducts: RecyclerView
    private lateinit var adapter: ProductAdapter



    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        rvProducts = root.findViewById(R.id.rvProducts)
        adapter = ProductAdapter(requireContext())
        rvProducts.adapter = adapter


        getProducts()
        return root
    }

    private fun getProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val repository = ProductRepository()
                val response = repository.getProducts()
                if (response.success == true) {
                    var products = response.data!!

                    val productDao = ProductDB.getInstance(requireContext()).getProductDAO()
                    productDao.deleteProducts()
                    for(product in products) {
                        productDao.insertProduct(product)
                    }

                    products = productDao.getAllProduct()
                    adapter.setList(products)

                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
package com.khadga.meromedapp.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.khadga.meromedapp.R
import com.khadga.meromedapp.adapter.CartAdapter
import com.khadga.meromedapp.db.ProductDB
import com.khadga.meromedapp.entity.Cart
import com.khadga.meromedapp.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CartFragment : Fragment() {

    private lateinit var rvCart : RecyclerView
    private lateinit var adapter : CartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)
        rvCart = view.findViewById(R.id.rvCart)
        adapter = CartAdapter(requireContext())
        rvCart.adapter = adapter
        getCartFromApi()
//        try {
//
//            getStoredCart()
//        } catch (ex: Exception) {
//            getStoredCart()
//            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
//        }
        return view
    }

    fun getCartFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = ProductRepository()
                val response = repository.getCart()
                val dao = ProductDB.getInstance(requireContext()).getCartDAO()
                if (response.success == true) {
                    for (cartItems in response.data!!) {
                        withContext(Dispatchers.Main) {
                           // Toast.makeText(context, cartItems.toString(), Toast.LENGTH_SHORT).show()
                        }
                        dao.deleteAll()
                        dao.insertCart(Cart(
                            _id = cartItems._id,
                            pName = cartItems.product_id.pName,
                            pDesc = cartItems.product_id.pDesc,
                            pImage = cartItems.product_id.pImage,
                            pPrice = cartItems.product_id.pPrice,
                            pRating = cartItems.product_id.pRating,
                            product_id = cartItems.product_id._id,
                            quantity = cartItems.quantity,
                            price = cartItems.price
                        ))
                    }
                    val cartItems = dao.getCartItems()
                    withContext(Dispatchers.Main) {
                        adapter.setList(cartItems)
                    }
                }
            }catch (Ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, Ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getStoredCart() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dao = ProductDB.getInstance(requireContext()).getCartDAO()
                val cartItems = dao.getCartItems()
                withContext(Dispatchers.Main) {
                    adapter.setList(cartItems)
                }
            }catch (Ex:Exception){
                withContext(Dispatchers.Main) {
                    Log.e("CartFragment", Ex.toString())
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
package com.khadga.meromedapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.khadga.meromedapp.R
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.db.ProductDB
import com.khadga.meromedapp.entity.Cart
import com.khadga.meromedapp.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CartAdapter(val context: Context): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    var cartList = mutableListOf<Cart>()
    inner class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivProductImage: ImageView
        val tvProductName : TextView
        val tvProductPrice: TextView
        val tvQuantity: TextView
        val ibDelete: ImageButton
        val btnIncrement: Button
        val btnDecrement: Button
        val tvTotalPrice: TextView

        init {
            ivProductImage = itemView.findViewById(R.id.ivProductImage)
            tvProductName = itemView.findViewById(R.id.tvProductName)
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice)
            tvQuantity = itemView.findViewById(R.id.tvQuantity)
            ibDelete = itemView.findViewById(R.id.ibDelete)
            btnDecrement = itemView.findViewById(R.id.btnDecrement)
            btnIncrement = itemView.findViewById(R.id.btnIncrement)
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_items, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = cartList[position]

        holder.tvProductPrice.text ="Rs. " + cart.price.toString()
        holder.tvProductName.text = cart.pName
        holder.tvQuantity.text = cart.quantity.toString()

        var imagePath = ServiceBuilder.loadImagePath()

        imagePath += cart.pImage
        imagePath = imagePath.replace("\\", "/")

        Glide.with(context).load(imagePath).into(holder.ivProductImage)

        val totalPrice = cart.price * cart.quantity
        holder.tvTotalPrice.text = "Total Price: $totalPrice"

        holder.ibDelete.setOnClickListener {
            // remove from current list
            Snackbar.make(holder.btnDecrement,"Deleted",Snackbar.LENGTH_LONG).show()
            cartList.removeAt(position)
            notifyDataSetChanged()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = ProductRepository()
                    val response = repository.deleteFromCart(cart._id)

                    val dao = ProductDB.getInstance(context).getCartDAO()
                    if (response.success == true) {
                        dao.deleteCartItems(cart)
                        withContext(Dispatchers.Main) {
                           Snackbar.make(holder.btnDecrement,"Deleted",Snackbar.LENGTH_LONG).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                       // Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        holder.btnIncrement.setOnClickListener {

            cartList[position].quantity++
            Toast.makeText(context, cartList[position].quantity.toString(), Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = ProductRepository()
                    val response = repository.updateFromCart(cart._id, cartList[position].quantity)

                    val dao = ProductDB.getInstance(context).getCartDAO()
                    if (response.success == true) {
                        dao.updateCartItems(cartList[position])
                        withContext(Dispatchers.Main) {
                           // Toast.makeText(context, "Added ${cart.pName}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                       // Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        holder.btnDecrement.setOnClickListener {
            if (cartList[position].quantity == 1) {
                Toast.makeText(context, "Can't Decrease. Please Delete", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            cartList[position].quantity--
            notifyDataSetChanged()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = ProductRepository()
                    val response = repository.updateFromCart(cart._id, cartList[position].quantity)

                    val dao = ProductDB.getInstance(context).getCartDAO()
                    if (response.success == true) {
                        dao.updateCartItems(cartList[position])
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "sub ${cart.pName}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun getItemCount() = cartList.size

    fun setList(list: List<Cart>) {
        cartList = list.toMutableList()
        notifyDataSetChanged()
    }
}
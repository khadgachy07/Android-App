package com.khadga.meromedapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.khadga.meromedapp.R
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.entity.Product
import com.khadga.meromedapp.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductAdapter(val context: Context): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productList = emptyList<Product>()
    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView
        val tvName: TextView
        val tvPrice : TextView
        val tvRating: TextView
        val btnAddToCart: Button

        init {
            ivImage = itemView.findViewById(R.id.ivImage)
            tvName = itemView.findViewById(R.id.tvName)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvRating = itemView.findViewById(R.id.tvRating)
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.tvName.text = product.pName
        holder.tvPrice.text = "Rs. ${product.pPrice}"
        holder.tvRating.text = product.pRating

        var imagePath = ServiceBuilder.loadImagePath()

        imagePath += product.pImage
        imagePath = imagePath.replace("\\", "/")

        Glide.with(context).load(imagePath).into(holder.ivImage)

        holder.btnAddToCart.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val pr = ProductRepository()
                    val response = pr.addToCart(product._id)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Snackbar.make(holder.btnAddToCart,"Added",Snackbar.LENGTH_LONG).show()
//                            Toast.makeText(context, "yo product${product._id} add vayo", Toast.LENGTH_SHORT)
//                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    override fun getItemCount() = productList.size

    fun setList(list: List<Product>) {
        productList = list
        notifyDataSetChanged()
    }
}
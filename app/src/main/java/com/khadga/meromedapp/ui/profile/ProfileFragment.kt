package com.khadga.meromedapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.khadga.meromedapp.LoginActivity
import com.khadga.meromedapp.R
import com.khadga.meromedapp.db.ProductDB
import com.khadga.meromedapp.notification.NotificationSender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProfileFragment : Fragment() {

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvPhone: TextView
    private lateinit var btnAddProduct: Button
    private lateinit var btnLogout: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvUsername = view.findViewById(R.id.tvUsername)
        tvPhone = view.findViewById(R.id.tvPhone)
        btnAddProduct = view.findViewById(R.id.btnAddProduct)
        btnLogout = view.findViewById(R.id.btnLogout)

        btnAddProduct.setOnClickListener {
            findNavController().navigate(R.id.navigation_dashboard)
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("username", "")
            editor.putString("password", "")
            editor.apply()
            startActivity(Intent(context, LoginActivity::class.java))
            deleteAllCartData()
        }
        getData()

        return view
    }

    fun deleteAllCartData() {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = ProductDB.getInstance(requireContext()).getCartDAO()
            dao.deleteAll()

            NotificationSender(requireContext(),"Logged Out!","See you soon.").createHighPriority()
        }
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dao = ProductDB.getInstance(requireContext()).getUserDAO()
                val user = dao.getUser()
                withContext(Dispatchers.Main) {
                    tvUsername.text = user.accUN
                    tvEmail.text = user.accEmail
                    tvPhone.text = user.accPhone
                    tvName.text = user.accFN
                    if (user.userType == "Customer") {
                        btnAddProduct.visibility = View.GONE
                    }
                }
            }catch (Ex:Exception){
                withContext(Dispatchers.Main) {
                    Log.e("CartFragment", Ex.toString())
                    Toast.makeText(context, "Error9", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
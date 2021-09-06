package com.khadga.meromedapp.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.khadga.meromedapp.R
import com.khadga.meromedapp.notification.NotificationSender
import com.khadga.meromedapp.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var ivImage: ImageView
    private lateinit var etName: TextInputEditText
    private lateinit var etDesc: TextInputEditText
    private lateinit var etPrice: TextInputEditText
    private lateinit var etRating: TextInputEditText
    private lateinit var btnAddProduct: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        ivImage = root.findViewById(R.id.ivImage)
        etName = root.findViewById(R.id.etName)
        etDesc = root.findViewById(R.id.etDesc)
        etPrice = root.findViewById(R.id.etPrice)
        etRating = root.findViewById(R.id.etRating)
        btnAddProduct = root.findViewById(R.id.btnAddProduct)

        ivImage.setOnClickListener {
            loadPopUpMenu()
        }
        btnAddProduct.setOnClickListener {
            addProduct()
        }
        return root
    }


    private fun addProduct() {
        val name = etName.text.toString()
        val desc = etDesc.text.toString()
        val price = etPrice.text.toString()
        val rating = etRating.text.toString()


        println(name)

        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val extension = MimeTypeMap.getFileExtensionFromUrl(imageUrl)
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            val reqFile =
                RequestBody.create(MediaType.parse(mimeType), file)
            val name: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                name
            )
            val desc: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                desc
            )
            val price: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                price
            )
            val rating: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                rating
            )
            val body =
                MultipartBody.Part.createFormData("pImage", file.name, reqFile)


            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = ProductRepository()
                    val response = repository.addProduct(name, desc, price, rating, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                            NotificationSender(
                                requireContext(),
                                "Product Added",
                                "Your Product is sent to cart."
                            ).createLowPriority()
                        }
                    }

                } catch (ex: Exception) {
                    println(ex.printStackTrace())
                    withContext(Dispatchers.Main) {

                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(context, ivImage)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = context?.contentResolver
                val cursor =
                    contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                ivImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                ivImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }


    }

    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
}
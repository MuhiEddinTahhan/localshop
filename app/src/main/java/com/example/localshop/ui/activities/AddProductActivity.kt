package com.example.localshop.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.localshop.R
import com.example.localshop.firestore.FirestoreClass
import com.example.localshop.models.Product
import com.example.localshop.utils.Constants
import com.example.localshop.utils.GlideLoader

import kotlinx.android.synthetic.main.activity_add_product.*
import java.io.IOException


class AddProductActivity : BaseActivity(), View.OnClickListener {

    private var mSelectedImageFileUri: Uri? = null

    private var mProductImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        setupActionBar()

        iv_add_update_product.setOnClickListener(this)

        btn_submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {

                R.id.iv_add_update_product -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this@AddProductActivity)
                    } else {

                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

                R.id.btn_submit -> {
                    if (validateProductDetails()) {

                        uploadProductImage()
                    }
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this@AddProductActivity)
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK
            && requestCode == Constants.PICK_IMAGE_REQUEST_CODE
            && data!!.data != null
        ) {

            iv_add_update_product.setImageDrawable(
                ContextCompat.getDrawable(
                    this@AddProductActivity,
                    R.drawable.ic_vector_edit
                )
            )

            mSelectedImageFileUri = data.data!!

            try {
                GlideLoader(this@AddProductActivity).loadProductPicture(
                    mSelectedImageFileUri!!,
                    iv_product_image
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun setupActionBar() {

        setSupportActionBar(toolbar_add_product_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_add_product_activity.setNavigationOnClickListener { onBackPressed() }
    }


    private fun validateProductDetails(): Boolean {
        return when {

            mSelectedImageFileUri == null -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_select_product_image), true)
                false
            }

            TextUtils.isEmpty(et_product_title.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_product_title), true)
                false
            }

            TextUtils.isEmpty(et_product_price.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_product_price), true)
                false
            }

            TextUtils.isEmpty(et_product_description.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_product_description),
                    true
                )
                false
            }

            TextUtils.isEmpty(et_product_quantity.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_product_quantity),
                    true
                )
                false
            }
            else -> {
                true
            }
        }
    }


    private fun uploadProductImage() {

        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().uploadImageToCloudStorage(
            this@AddProductActivity,
            mSelectedImageFileUri,
            Constants.PRODUCT_IMAGE
        )
    }

    fun imageUploadSuccess(imageURL: String) {

        mProductImageURL = imageURL

        uploadProductDetails()
    }

    private fun uploadProductDetails() {

        val username =
            this.getSharedPreferences(Constants.MYSHOPPAL_PREFERENCES, Context.MODE_PRIVATE)
                .getString(Constants.LOGGED_IN_USERNAME, "")!!

        val product = Product(
            FirestoreClass().getCurrentUserID(),
            username,
            et_product_title.text.toString().trim { it <= ' ' },
            et_product_price.text.toString().trim { it <= ' ' },
            et_product_description.text.toString().trim { it <= ' ' },
            et_product_quantity.text.toString().trim { it <= ' ' },
            mProductImageURL
        )

        FirestoreClass().uploadProductDetails(this@AddProductActivity, product)
    }


    fun productUploadSuccess() {

        hideProgressDialog()

        Toast.makeText(
            this@AddProductActivity,
            resources.getString(R.string.product_uploaded_success_message),
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}
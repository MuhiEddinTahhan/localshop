package com.example.localshop.ui.fragments

import android.app.Dialog
import androidx.fragment.app.Fragment
import com.example.localshop.R

import kotlinx.android.synthetic.main.dialog_progress.*


open class BaseFragment : Fragment() {


    private lateinit var mProgressDialog: Dialog

    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(requireActivity())


        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_progress_text.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()
    }


    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }
}
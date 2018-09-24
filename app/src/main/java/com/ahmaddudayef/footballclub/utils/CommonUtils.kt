package com.ahmaddudayef.footballclub.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.ahmaddudayef.footballclub.R

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
object CommonUtils {

    fun showLoadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun comma(str: String?): String? = str?.trim()?.replace(";", "\n")
}
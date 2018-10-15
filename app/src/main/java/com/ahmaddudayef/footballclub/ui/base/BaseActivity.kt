package com.ahmaddudayef.footballclub.ui.base

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ahmaddudayef.footballclub.utils.CommonUtils

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
open class BaseActivity: AppCompatActivity(), MvpView {

    private var progressDialog: Dialog? = null

    fun hasPermission(permission: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true
        }
        return permission.none {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissionSafely(permission: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permission, requestCode)
        }
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressDialog!!.cancel()
        }
    }

    override fun showMessage(message: String) {
        displayMessage(message)
    }

    override fun showError(message: String) {
        displayMessage(message)
    }

}
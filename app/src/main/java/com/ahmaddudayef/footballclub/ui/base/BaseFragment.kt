package com.ahmaddudayef.footballclub.ui.base

import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.ahmaddudayef.footballclub.utils.CommonUtils

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
abstract class BaseFragment: Fragment(), MvpView {
    private var baseActivity: BaseActivity? = null
    private var progressDialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity){
            baseActivity = context
        }
    }

    protected abstract fun setUp(view: View)

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this.context!!)
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.cancel()
        }
    }

    override fun showMessage(message: String) {
        if (baseActivity != null)
            baseActivity!!.showMessage(message)
    }

    override fun showError(message: String) {
        if (baseActivity != null)
            baseActivity!!.showError(message)
    }

    fun getBaseActivity(): BaseActivity? {
        return baseActivity
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true
        return permissions.none {
            ContextCompat.checkSelfPermission(
                    getBaseActivity()!!,
                    it
            ) != PackageManager.PERMISSION_GRANTED
        }
    }
}
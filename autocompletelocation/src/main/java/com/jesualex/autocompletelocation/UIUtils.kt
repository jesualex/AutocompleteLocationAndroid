package com.jesualex.autocompletelocation

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object UIUtils {
    fun hideKeyboard(context: Context, view: View?): Boolean {
        if (view != null) {
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            return inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        return false
    }
}

package com.debicki.mdbpreview.common

import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewExtensions {

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(InputMethodManager::class.java)
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun Boolean.toVisibility(): Int {
        return if (this) View.VISIBLE else View.GONE
    }
}

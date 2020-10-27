package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 *
 * Created by Nikolay Tkachenko on 27, October, 2020
 *
 */
fun hideKeyboard(activity : Activity){
    val inputMethodManager : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFicusView : View? = activity.currentFocus

    currentFicusView?.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFicusView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}
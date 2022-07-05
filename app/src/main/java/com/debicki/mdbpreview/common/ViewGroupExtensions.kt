package com.debicki.mdbpreview.common

import android.view.LayoutInflater
import android.view.ViewGroup

object ViewGroupExtensions {

    fun ViewGroup.layoutInflater() = LayoutInflater.from(context)

}
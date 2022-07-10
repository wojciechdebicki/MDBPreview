package com.debicki.mdbpreview.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String) = Picasso.get().load(url).into(this)

fun ImageView.cancelRequest() = Picasso.get().cancelRequest(this)

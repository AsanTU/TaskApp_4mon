package io.muhsin.taskapp.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.muhsin.taskapp.R


fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadImageGallery(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_person).into(this)
}
package com.example.newapp.common

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newapp.R
import java.text.SimpleDateFormat
import java.util.Calendar

fun localValue(valueAr: String, valueEn: String): String {
//   return if (AppConfig.getPreferences().second == Constants.DEFAULT_LANGUAGE) {
//      valueAr
//   } else {
    return valueEn
//   }
}

fun <T> indexOfListByPredicate(categories: List<T>, predicate: (T) -> Boolean): Int? {
    return categories.indexOfFirst(predicate)
}

@SuppressLint("SimpleDateFormat")
fun format(time: Long): String {
    val format = SimpleDateFormat("dd MMM hh:mm")
    return format.format(time)

}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_photo)
        .into(this)
}

@SuppressLint("SimpleDateFormat")
fun convertDate(date: String): Long {
    var dt = date.replace('T', ' ')
    dt = dt.replace('Z', ' ')
    val d = Calendar.getInstance()
    try {
        val i = SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").parse(dt)
        if (i != null) {
            d.timeInMillis = i.time
        }
    } catch (e: Exception) {

    }
    return d.timeInMillis
}

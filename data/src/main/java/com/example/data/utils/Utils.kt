package com.example.data.utils

import android.net.Uri

fun String.getImageUrl(size: Int = 500): Uri {
    return this.let {
        val url = "https://cdn-images.dzcdn.net/images/cover/"
        val jpgPostfix = "-000000-80-0-0.jpg"
        Uri.parse("$url$it/${size}x$size$jpgPostfix")
    }
}
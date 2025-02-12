package com.example.core.utils

fun String?.getImageUrl(size: Int = 100): String? {
    return this?.let {
        val url = "https://cdn-images.dzcdn.net/images/cover/"
        val jpgPostfix = "-000000-80-0-0.jpg"
        "$url$it/${size}x$size$jpgPostfix"
    }
}
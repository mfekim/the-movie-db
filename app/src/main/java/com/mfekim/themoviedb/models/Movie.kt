package com.mfekim.themoviedb.models

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("original_title") val originalTitle: String)
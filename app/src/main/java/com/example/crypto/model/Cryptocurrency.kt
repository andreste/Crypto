package com.example.crypto.model

import com.google.gson.annotations.SerializedName

data class Cryptocurrency(
    var id: String,
    @SerializedName("current_price") var price: Float
)

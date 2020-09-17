package com.example.crypto

import com.example.crypto.model.Cryptocurrency
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoService {

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=5&page=1&sparkline=false")
    fun getCryptocurrencies(): Single<List<Cryptocurrency>>
}

package com.example.crypto

import com.example.crypto.model.Cryptocurrency
import io.reactivex.Single

class CryptoRepository
constructor(
    private val cryptoService: CryptoService
) {
    fun fetchCryptocurrencies(): Single<List<Cryptocurrency>> {
        return cryptoService.getCryptocurrencies()
    }
}

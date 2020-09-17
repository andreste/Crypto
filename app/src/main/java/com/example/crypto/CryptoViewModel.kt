package com.example.crypto

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val cryptoService: CryptoService = retrofit.create(CryptoService::class.java)
    private val cryptoRepository = CryptoRepository(cryptoService)

    fun fetchCryptocurrencies() {
        compositeDisposable.clear()
        compositeDisposable.add(
            cryptoRepository.fetchCryptocurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    Log.d("", response.joinToString(", "))
                },
                    { er ->
                        Log.e("", "error")
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}


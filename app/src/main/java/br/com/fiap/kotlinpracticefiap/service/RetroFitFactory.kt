package br.com.fiap.kotlinpracticefiap.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitFactory {
    
    private val URL = "https://viacep.com.br/ws/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCepService(): CepService {
        return retrofitFactory.create(CepService::class.java)
    }
}
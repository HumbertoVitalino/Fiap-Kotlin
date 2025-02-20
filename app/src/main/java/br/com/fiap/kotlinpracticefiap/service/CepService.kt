package br.com.fiap.kotlinpracticefiap.service

import br.com.fiap.kotlinpracticefiap.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface CepService {

    @GET("{cep}/json/")
    fun getEndereco(@Path("cep") cep: String): Call<Endereco>

    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecos(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Endereco>>
}

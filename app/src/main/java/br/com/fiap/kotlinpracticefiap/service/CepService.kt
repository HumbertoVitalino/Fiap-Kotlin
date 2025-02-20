package br.com.fiap.kotlinpracticefiap.service

import br.com.fiap.kotlinpracticefiap.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface CepService {

    //https://viacep.com.br/ws/01001000/json
    @GET("{cep}/json/")
    fun getEndereco(@Path("cep") cep: String): Call<Endereco>

    //https://viacep.com.br/ws/RS/Porto%20Alegre/Domingos/json/
    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecos(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): List<Call<Endereco>>
}

package br.com.fiap.kotlinpracticefiap.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.kotlinpracticefiap.model.Endereco
import br.com.fiap.kotlinpracticefiap.service.RetroFitFactory
import br.com.fiap.kotlinpracticefiap.ui.theme.FiapkotlinTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CepScreen() {
    var cepState by remember { mutableStateOf("") }
    var ufState by remember { mutableStateOf("") }
    var cidadeState by remember { mutableStateOf("") }
    var ruaState by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var listaCepsState by remember { mutableStateOf(listOf<Endereco>()) }
    var enderecoState by remember { mutableStateOf(Endereco()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "CONSULTA CEP", fontSize = 24.sp, color = Color.Black)
                Text(
                    text = "Encontre o seu endereço",
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = cepState,
                    onValueChange = { cepState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Qual o CEP procurado?") },
                    trailingIcon = {
                        IconButton(onClick = {
                            errorMessage = ""
                            val call = RetroFitFactory()
                                .getCepService()
                                .getEndereco(cep = cepState)
                            call.enqueue(object : Callback<Endereco> {
                                override fun onResponse(
                                    call: Call<Endereco>,
                                    response: Response<Endereco>
                                ) {
                                    if (response.isSuccessful) {
                                        enderecoState = response.body() ?: Endereco()
                                    } else {
                                        errorMessage = "Erro: CEP não encontrado"
                                    }
                                }

                                override fun onFailure(call: Call<Endereco>, t: Throwable) {
                                    errorMessage = "Erro ao buscar CEP: ${t.message}"
                                }
                            })
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Pesquisar"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Não sabe o CEP?",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    OutlinedTextField(
                        value = ufState,
                        onValueChange = { ufState = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        label = { Text(text = "UF?") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    OutlinedTextField(
                        value = cidadeState,
                        onValueChange = { cidadeState = it },
                        modifier = Modifier.weight(2f),
                        label = { Text(text = "Qual a cidade?") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = ruaState,
                        onValueChange = { ruaState = it },
                        modifier = Modifier.weight(2f),
                        label = { Text(text = "O que lembra do nome da rua?") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                    IconButton(onClick = {
                        errorMessage = ""
                        val call = RetroFitFactory()
                            .getCepService()
                            .getEnderecos(
                                uf = ufState,
                                cidade = cidadeState,
                                rua = ruaState
                            )
                        call.enqueue(object : Callback<List<Endereco>> {
                            override fun onResponse(
                                call: Call<List<Endereco>>,
                                response: Response<List<Endereco>>
                            ) {
                                if (response.isSuccessful) {
                                    listaCepsState = response.body() ?: emptyList()
                                } else {
                                    errorMessage = "Erro: Nenhum endereço encontrado"
                                }
                            }

                            override fun onFailure(call: Call<List<Endereco>>, t: Throwable) {
                                errorMessage = "Erro ao buscar endereços: ${t.message}"
                            }
                        })
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Pesquisar Rua")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(listaCepsState) {
                AddressCard(it)
            }
        }
    }
}

@Composable
fun AddressCard(address: Endereco) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "CEP: ${address.cep}")
            Text(text = "Rua: ${address.rua}")
            Text(text = "Cidade: ${address.cidade}")
            Text(text = "Bairro: ${address.bairro}")
            Text(text = "UF: ${address.uf}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FiapkotlinTheme {
        br.com.fiap.kotlinpracticefiap.screens.CepScreen()
    }
}

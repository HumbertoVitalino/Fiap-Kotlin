package br.com.fiap.kotlinpracticefiap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.kotlinpracticefiap.screens.CepScreen
import br.com.fiap.kotlinpracticefiap.ui.theme.FiapkotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiapkotlinTheme {
                CepScreen()
            }
        }
    }
}

package com.example.p8_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p8_mvvm.ui.theme.P8_MVVMTheme
import com.example.p8_mvvm.view.FormIsian
import com.example.p8_mvvm.view.TampilData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = "form"){
                composable("form"){
                    FormIsian(
                        OnSubmitBtnClick = {nav.navigate("tampil")}
                    )
                }
                composable("tampil"){
                    TampilData(
                        onBackBtnClick = {nav.navigate("form")}
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    P8_MVVMTheme {
        Greeting("Android")
    }
}
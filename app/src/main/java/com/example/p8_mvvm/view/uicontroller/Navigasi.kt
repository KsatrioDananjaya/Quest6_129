package com.example.p8_mvvm.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p8_mvvm.model.Siswa
import com.example.p8_mvvm.view.FormIsian
import com.example.p8_mvvm.view.TampilData
import com.example.p8_mvvm.viewmodel.SiswaViewModel
import com.example.p8_mvvm.model.DataJK.JenisK

enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun SiswaApp(
    modifier: Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(paddingValues = isiRuang)){
            composable(route = Navigasi.Formulir.name){
                val konteks = LocalContext.current
                FormIsian(
                    pilihanJK = JenisK.map { id -> konteks.resources.getString(id)},
                    onSubmitBtnClicked = {viewModel.setSiswa(it)
                            navController.navigate(route = Navigasi.Detail.name)}
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData(
                    statusUiSiswa = uiState.value,
                    onBackBtnClicked = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }

}
private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulir.name, inclusive = false)
}
package com.example.prjimodbiliaria.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prjimodbiliaria.Screens.CadastroScreen
import com.example.prjimodbiliaria.Screens.ConsultarScreen
import com.example.prjimodbiliaria.Screens.EditarScreen
import com.example.prjimodbiliaria.Screens.HomeScreen
import com.example.prjimodbiliaria.Screens.SobreScreen
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel


@Composable
fun NavigationGraph(navController: NavHostController, viewModel: ImovelViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Profile.route) {
            CadastroScreen(viewModel = viewModel, )
        }
        composable(BottomNavItem.Settings.route) {
            ConsultarScreen(viewModel = viewModel, navController = navController)
        }
        composable(BottomNavItem.Sobre.route) {
            SobreScreen()
        }
        composable("editar/{id}") { backStackEntry ->
            val idString = backStackEntry.arguments?.getString("id")
            val id = idString?.toIntOrNull() // Converte para Int ou null se falhar
            if (id != null) {
                EditarScreen(viewModel = viewModel, id = id,  navController = navController) // Passando o ID para a tela de edição
            } else {
                // Lidar com o erro, como mostrar uma mensagem ou redirecionar
            }
        }
    }
}

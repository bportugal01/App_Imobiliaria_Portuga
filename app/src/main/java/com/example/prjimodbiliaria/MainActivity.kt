package com.example.prjimodbiliaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.prjimodbiliaria.Factory.ImovelViewModelFactory
import com.example.prjimodbiliaria.Navigation.BottomNavItem
import com.example.prjimodbiliaria.Navigation.BottomNavigationBar
import com.example.prjimodbiliaria.Navigation.MyTopAppBar
import com.example.prjimodbiliaria.Navigation.NavigationGraph
import com.example.prjimodbiliaria.Repository.ImovelRepository
import com.example.prjimodbiliaria.RoomDB.ImovelDataBase
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel
import com.example.prjimodbiliaria.ui.theme.PrjImodbiliariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializando o banco de dados e repositório
        val database = ImovelDataBase.getDatabase(this)
        val repository = ImovelRepository(database.imovelDao())
        val viewModelFactory = ImovelViewModelFactory(repository)
        val viewModel: ImovelViewModel by viewModels { viewModelFactory }

        setContent {
            PrjImodbiliariaTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: ImovelViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = backStackEntry.value?.destination?.route

            MyTopAppBar(
                navController = navController,
                title = when (currentScreen) {
                    BottomNavItem.Home.route -> "Imobiliaria Portuga"
                    BottomNavItem.Profile.route -> "Cadastro de Imóveis"
                    BottomNavItem.Settings.route -> "Consultar Imóveis"
                    BottomNavItem.Sobre.route -> "Sobre"
                    "editar/{id}" -> "Edição de Imóveis" // Adicione esta linha
                    else -> "Imobiliaria Portuga"
                },
                showBackButton = currentScreen != BottomNavItem.Home.route
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationGraph(navController = navController, viewModel = viewModel)
        }
    }
}
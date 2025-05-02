package com.example.testappdl

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.NavRoutes.MAIN_SCREEN
import com.example.testappdl.NavRoutes.REGISTER_SCREEN
import com.example.testappdl.ui.screens.DetailScreen
import com.example.testappdl.ui.screens.LoginScreen
import com.example.testappdl.ui.screens.MainScreen
import com.example.testappdl.ui.theme.TestAppDLTheme
import com.example.testappdl.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

object NavRoutes {
    const val REGISTER_SCREEN = "register_screen"
    const val MAIN_SCREEN = "main_screen"
    const val DETAIL_SCREEN = "detail_screen"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TEST","NEWActivity")
        enableEdgeToEdge()
        setContent {
            TestAppDLTheme {
                Navigation()

            }
        }
    }
}

@Composable
fun Navigation(){
    val navHostController = rememberNavController()

    fun navigateTo(route:String){
        navHostController.navigate(route)
    }
    fun navigateToWithoutPopUP(route:String){
        navHostController.navigate(route){
            popUpTo(0)
        }
    }


    fun popBackStack(){
        navHostController.popBackStack()
    }

    NavHost(navController = navHostController, startDestination = REGISTER_SCREEN){
        composable(REGISTER_SCREEN){ LoginScreen(navigate = { route -> navigateToWithoutPopUP(route) } ) }
        composable(MAIN_SCREEN) { MainScreen(navigate = { route -> navigateTo(route, ) }) }
        composable(DETAIL_SCREEN) { DetailScreen(navigate = { route -> navigateTo(route) }) }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    TestAppDLTheme {
        Navigation()
    }
}


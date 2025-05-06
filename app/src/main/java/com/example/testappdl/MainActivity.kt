package com.example.testappdl

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testappdl.NavRoutes.ADD_TO_DATABASE_SCREEN
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.NavRoutes.MAIN_SCREEN
import com.example.testappdl.NavRoutes.REGISTER_SCREEN
import com.example.testappdl.rep.UserRepository
import com.example.testappdl.ui.screens.AddScreen
import com.example.testappdl.ui.screens.DetailScreen
import com.example.testappdl.ui.screens.LoginScreen
import com.example.testappdl.ui.screens.MainScreen
import com.example.testappdl.ui.theme.TestAppDLTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

object NavRoutes {
    const val REGISTER_SCREEN = "register_screen"
    const val MAIN_SCREEN = "main_screen"
    const val DETAIL_SCREEN = "detail_screen"
    const val ADD_TO_DATABASE_SCREEN = "add_to_base"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TEST","NEWActivity")
        enableEdgeToEdge()
        setContent {
            TestAppDLTheme() {
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

    NavHost(
        navController = navHostController,
        startDestination = REGISTER_SCREEN
    ) {
        composable(REGISTER_SCREEN) {
            LoginScreen(navigate = { route -> navigateToWithoutPopUP(route) })
        }
        composable(MAIN_SCREEN) {
            MainScreen(navigate = { route -> navigateTo(route) })
        }
        composable(ADD_TO_DATABASE_SCREEN) {
            AddScreen(navigate = { route -> navigateToWithoutPopUP(route) })
        }

        composable(
            DETAIL_SCREEN+"/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            DetailScreen(
                itemId = itemId,
                navigate = { route -> navigateTo(route) }
            )
        }

    }

}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    TestAppDLTheme {
        Navigation()
    }
}


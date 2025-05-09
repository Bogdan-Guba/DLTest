package com.example.testappdl


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testappdl.NavRoutes.ADD_TO_DATABASE_SCREEN
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.NavRoutes.HOME_SCREEN
import com.example.testappdl.NavRoutes.REGISTER_SCREEN
import com.example.testappdl.component.BottomNavItem
import com.example.testappdl.component.BottomNavigationBar
import com.example.testappdl.ui.screens.AddScreen
import com.example.testappdl.ui.screens.DetailScreen
import com.example.testappdl.ui.screens.LoginScreen
import com.example.testappdl.ui.screens.HomeScreen
import com.example.testappdl.ui.screens.SettingScreen
import com.example.testappdl.ui.theme.TestAppDLTheme
import com.example.testappdl.viewModel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

object NavRoutes {
    const val REGISTER_SCREEN = "register_screen"
    const val HOME_SCREEN = "main_screen"
    const val DETAIL_SCREEN = "detail_screen"
    const val ADD_TO_DATABASE_SCREEN = "add_to_base"
    const val SETTING_SCREEN = "settings_screen"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity () {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TEST","NEWActivity")
        enableEdgeToEdge()
        setContent {

            MainScreen()
        }
    }


}


@Composable
fun MainScreen(

    themeViewModel: ThemeViewModel = hiltViewModel()
) {

    val isDarkTheme = themeViewModel.isDarkTheme.collectAsState().value


    TestAppDLTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()

        val bottomBarRoutes = listOf(

            BottomNavItem.Home.route,
            BottomNavItem.Settings.route

        )

        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route


        Scaffold(
            bottomBar = {

                if (bottomBarRoutes.contains(currentRoute)) {
                    BottomNavigationBar(navController = navController)
                }

            }

        ) { paddingValues ->NavHost(
            navController = navController,

            startDestination = NavRoutes.REGISTER_SCREEN,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            composable(NavRoutes.REGISTER_SCREEN) {

                LoginScreen(
                    navigate = {
                        navController.navigate(NavRoutes.HOME_SCREEN) {
                            popUpTo(NavRoutes.REGISTER_SCREEN) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(NavRoutes.HOME_SCREEN) {
                HomeScreen(
                    navigate = navController::navigate
                )
            }
            composable(NavRoutes.SETTING_SCREEN) {
                SettingScreen()
            }


            composable(NavRoutes.ADD_TO_DATABASE_SCREEN) {
                AddScreen(

                    navigate = navController::navigate

                )
            }


            composable(
                "${NavRoutes.DETAIL_SCREEN}/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments!!.getInt("itemId")
                DetailScreen(
                    itemId = itemId,
                    navigate = navController::navigate

                )
            }


        }


        }
    }
}






//@Preview(showBackground = true)
//@Composable
//fun PreviewMainScreen() {
//    TestAppDLTheme {
//        Navigation()
//    }
//}


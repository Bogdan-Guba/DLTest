package com.example.testappdl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testappdl.ui.screens.DetailScreen
import com.example.testappdl.ui.screens.LoginScreen
import com.example.testappdl.ui.screens.MainScreen
import com.example.testappdl.ui.theme.TestAppDLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestAppDLTheme {
                Navigation();
            }
        }
    }
}

@Composable
fun Navigation(){
    val NavHostController = rememberNavController()
    NavHost(navController = NavHostController, startDestination = "register_screen"){
        composable("register_screen"){LoginScreen(NavHostController)}
        composable("main_screen") { MainScreen(NavHostController) }
        composable("detail_screen") { DetailScreen(NavHostController)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    TestAppDLTheme {
        LoginScreen(navController = rememberNavController())
    }
}


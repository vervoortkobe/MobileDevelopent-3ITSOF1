package edu.ap.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { Screen1(navController) }
                        composable(
                            route = "screen2/{name}",
                            arguments = listOf(navArgument("name") { type = NavType.StringType })
                        ) { backStackEntry ->
                            Screen2(
                                navController,
                                name = backStackEntry.arguments?.getString("name") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Screen 1", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("screen2/JohnDoe") }) {
            Text(text = "Navigate to Screen 2 with Name")
        }
    }
}

@Composable
fun Screen2(navController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Screen 2", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: $name", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("screen1") }) {
            Text(text = "Navigate to Screen 1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screen1") {
                composable("screen1") { Screen1(navController) }
                composable(
                    route = "screen2/{name}",
                    arguments = listOf(navArgument("name") { type = NavType.StringType })
                ) { backStackEntry ->
                    Screen2(
                        navController,
                        name = backStackEntry.arguments?.getString("name") ?: ""
                    )
                }
            }
        }
    }
}
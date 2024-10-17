package edu.ap.userdetails

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(this)

        setContent {
            UserInputScreen(userPreferences)
        }

        printStoredValues()
    }

    private fun printStoredValues() {

        lifecycleScope.launch {
            userPreferences.userName.collect { name ->
                Log.d("DataStore", "Stored Name: $name")
                userPreferences.email.collect { email ->
                    Log.d("DataStore", "Stored Email: $email")
                    userPreferences.password.collect { password ->
                        Log.d("DataStore", "Stored Password: $password")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(userPreferences: UserPreferences) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                userPreferences.saveUser(name, email, password)
            }
        }) {
            Text("Save")
        }
    }
}

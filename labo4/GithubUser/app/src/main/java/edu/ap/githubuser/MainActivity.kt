package edu.ap.githubuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ap.githubuser.retrofit.UserResponse
import edu.ap.githubuser.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserApp()
        }
    }
}

@Composable
fun GithubUserApp() {
    var username by remember { mutableStateOf("") }
    var userResponse by remember { mutableStateOf<UserResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                isLoading = true
                RetrofitClient.instance.getUser(username).enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        userResponse = response.body()
                        isLoading = false
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        userResponse = null
                        isLoading = false
                    }
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fetch User")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column {
                userResponse?.let {
                    Text("User: ${it.login}")
                } ?: Text("User: ")
                userResponse?.let {
                    Text("Avatar URL: ${it.avatarUrl}")
                } ?: Text("Avatar URL: ")
                userResponse?.let {
                    Text("Id: ${it.id}")
                } ?: Text("Id: ")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubUserApp()
}
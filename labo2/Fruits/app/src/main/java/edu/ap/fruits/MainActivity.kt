package edu.ap.fruits

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

data class Fruit(val name: String, val calories: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FruitList(fruits = getFruitList())
                }
            }
        }
    }

    private fun getFruitList(): List<Fruit> {
        return listOf(
            Fruit("Apple", 52),
            Fruit("Banana", 96),
            Fruit("Cherry", 50),
            Fruit("Date", 282),
            Fruit("Fig", 74),
            Fruit("Grapes", 67),
            Fruit("Mango", 60)
        )
    }
}

@Composable
fun FruitList(fruits: List<Fruit>) {
    LazyColumn {
        items(fruits) { fruit ->
            FruitItem(fruit)
        }
    }
}

@Composable
fun FruitItem(fruit: Fruit) {
    Text(
        text = fruit.name,
        fontSize = 24.sp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                Log.d("FruitListApp", "${fruit.name}: ${fruit.calories} calories")
            }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        FruitList(fruits = listOf(
            Fruit("Apple", 52),
            Fruit("Banana", 96),
            Fruit("Cherry", 50),
            Fruit("Date", 282),
            Fruit("Fig", 74),
            Fruit("Grapes", 67),
            Fruit("Mango", 60)
        ))
    }
}
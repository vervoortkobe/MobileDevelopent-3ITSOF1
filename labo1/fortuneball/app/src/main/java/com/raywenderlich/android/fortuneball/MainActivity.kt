package com.raywenderlich.android.fortuneball

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var fortuneList = arrayOf(
        "Don’t count on it",
        "Ask again later",
        "You can rely on it",
        "Without a doubt",
        "Outlook is not so good",
        "It's decidedly so",
        "Signs point to yes",
        "Yes, definitely",
        "Yes",
        "My sources say NO"
    )
    private lateinit var fortuneText: TextView
    private lateinit var generateFortuneButton: Button
    private lateinit var fortuneBallImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fortuneText = findViewById<View>(R.id.fortuneText) as TextView
        fortuneBallImage = findViewById<View>(R.id.fortunateImage) as ImageView
        generateFortuneButton = findViewById<View>(R.id.fortuneButton) as Button

        generateFortuneButton.setOnClickListener {
            // 5:
            val index = Random().nextInt(fortuneList.size)
            fortuneText.setText(fortuneList[index])
            // 6:
            YoYo.with(Techniques.Swing)
                .duration(500)
                .playOn(fortunateImage)
        }
        Log.v("fortuneballTag", "onCreateCalled")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package edu.ap.handlerthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity
import edu.ap.handlerthread.databinding.ActivityMainBinding
import java.net.URL
import okhttp3.*
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val url = URL("https://api.github.com/search/users?q=ppossemiers")
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())

        // met Handler
        myExecutor.execute {
            val result = getURLContentsAsString(url)
            myHandler.post {
                binding.ourTextView.text = result
            }
        }

        // met Thread
        /*Thread(Runnable {
            val result = getURLContentsAsString(url)
            runOnUiThread {
                binding.ourTextView.text = result
            }
        }).start()*/
    }

    fun getURLContentsAsString(ourUrl: URL): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(ourUrl)
            .build()

        val call = client.newCall(request)
        val response = call.execute()

        return response.body!!.string()
    }
}

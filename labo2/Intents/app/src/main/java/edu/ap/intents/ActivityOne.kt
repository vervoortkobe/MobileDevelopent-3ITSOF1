package edu.ap.intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ActivityOne : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityTwoBinding.
        setContentView(R.layout.activity_activity_one)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    }
}

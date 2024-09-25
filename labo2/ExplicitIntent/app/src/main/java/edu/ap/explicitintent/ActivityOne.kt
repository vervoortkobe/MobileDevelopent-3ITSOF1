package edu.ap.explicitintent

import android.os.Bundle
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityOne : Activity() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_one)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
     }
}

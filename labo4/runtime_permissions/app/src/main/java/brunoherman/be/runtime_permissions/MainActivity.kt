package brunoherman.be.runtime_permissions

import android.Manifest
import android.app.AlertDialog //
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var btnCamera : Button
    lateinit var btnStorage : Button
    private val CAMERA_PERMISSION_CODE = 123
    private val STORAGE_PERMISSION_CODE = 113


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera = findViewById(R.id.btnCamera)
        btnStorage = findViewById(R.id.btnStorage)

        btnCamera.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
        //region newcode
        /*
            when {
                ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CAMERA


                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    Toast.makeText(this@MainActivity, "Permission Granted already", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(...) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                Toast.makeText(this@MainActivity, "I really want camera permission", Toast.LENGTH_SHORT).show()
            }
                else -> {
                    // You can directly ask for the permission.
                    requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.CAMERA),
                                CAMERA_PERMISSION_CODE
                        )
                    )
                }
            }

             */
        //endregion
        }

        btnStorage.setOnClickListener {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
        }
    }

    //global method for reuse !!
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {
            //this is where a request is made
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        }
        else {
            Toast.makeText(this@MainActivity, "Permission Granted already", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setText("Camera Permission Granted")
                Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setText("Storage Permission Granted")
                Toast.makeText(this@MainActivity, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
        else {

        }
    }

    //region new style
    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher. You can use either a val, as shown in this snippet,
    // or a lateinit var in your onAttach() or onCreate() method.
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    //endregion
}
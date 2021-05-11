package com.example.permissiondemo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var btn_permission: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_permission = findViewById(R.id.btn_permission)
        btn_permission.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED

            ) {
                Toast.makeText(this, "grant", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA
                    , Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    CAMERA_AND_FINE_REQUEST_CODE
                )
            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1
        private const val FINE_LOCATION_REQUEST_CODE = 1
        private const val CAMERA_AND_FINE_REQUEST_CODE = 12
    }

}
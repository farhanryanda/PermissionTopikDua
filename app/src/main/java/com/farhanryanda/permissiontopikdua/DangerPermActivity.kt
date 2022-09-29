package com.farhanryanda.permissiontopikdua

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.farhanryanda.permissiontopikdua.databinding.ActivityDangerPermBinding

class DangerPermActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDangerPermBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangerPermBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetloc.setOnClickListener {
            val permissionCheck =
                checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Location DIIZINKAN", Toast.LENGTH_LONG).show()
                getLongLat()
            } else {
                Toast.makeText(this, "Permission Location DITOLAK", Toast.LENGTH_LONG).show()
                requestLocationPermission()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestLocationPermission() {
        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 201)
    }

    @SuppressLint("MissingPermission")
    fun getLongLat() {
        val locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        val latLongText = "Lat: ${location?.latitude} Long : ${location?.longitude}"
        Log.d("latlong", latLongText)
        Toast.makeText(this, latLongText, Toast.LENGTH_LONG).show()

//        val intent = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse("geo:${location?.latitude},${location?.longitude}")
//        )
//        intent.setPackage("com.google.android.apps.maps")
//        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            201 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION
                ) {
                    Toast.makeText(this, "Permissions for Location Permitted", Toast.LENGTH_LONG)
                        .show()
                    getLongLat()
                } else {
                    Toast.makeText(this, "Permissions for Location Denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> {
                Toast.makeText(this, "The request code doesn't match", Toast.LENGTH_LONG).show()
            }
        }
    }
}
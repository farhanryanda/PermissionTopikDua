package com.farhanryanda.permissiontopikdua

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.farhanryanda.permissiontopikdua.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetimg.setOnClickListener {
            Glide.with(this)
                .load("https://media.istockphoto.com/vectors/cartoon-cute-beagle-puppy-vector-character-mascot-vector-id1180989037?k=20&m=1180989037&s=612x612&w=0&h=7LRREi55KTZUNdW9eTKxp3iyYhnT7GaVvwA_LoP3jjE=")
                .circleCrop()
                .into(binding.imgGambar)
        }
        binding.btnGetimg2.setOnClickListener {
            Glide.with(this)
                .load("https://img.freepik.com/free-vector/one-cute-dog-white-background_1308-44313.jpg?w=360")
                .circleCrop()
                .into(binding.imgGambar)
        }

        binding.btnCheckinet.setOnClickListener {
            val connectionManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi_Connection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile_data_connection =
                connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

            if (wifi_Connection!!.isConnectedOrConnecting) {
                Toast.makeText(this, "Internet Terkoneksi", Toast.LENGTH_SHORT).show()
            } else {
                if (mobile_data_connection!!.isConnectedOrConnecting) {
                    Toast.makeText(this, "Mobile Data Terkoneksi", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Internet Tidak Terkoneksi", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.btnDangerperm.setOnClickListener {
            val intent = Intent(this, DangerPermActivity::class.java)
            startActivity(intent)
        }

    }
}
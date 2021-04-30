package com.example.loginmysqlbachtiar

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class dashboard : AppCompatActivity() {
    private lateinit var nama : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        nama = findViewById(R.id.tv_nama)

        val URL = "http://192.168.18.248/lgn/dashboard.php"
        val jsonObject = JSONObject()
        val requestQueue = Volley.newRequestQueue(this)

        val pesan = intent.getStringExtra("Pesan")
        nama.text = "Selamat datang,"+pesan


        val btnLogout = findViewById<Button>(R.id.btn_logout)

        btnLogout.setOnClickListener {
            val prompt = AlertDialog.Builder(this)
            prompt.setTitle("Keluar Aplikasi")
            prompt.setMessage("Yakin akan keluar?")
            prompt.setPositiveButton("Ya", { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, "Anda berhasil logout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,login::class.java)
                startActivity(intent)
            })
            prompt.setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
            prompt.show()

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, URL, jsonObject, Response.Listener {
                response ->
                nama.text = "Selamat Datang ${response.getString("nama")}" },
                    Response.ErrorListener {
                        error ->
                    })
            requestQueue.add(jsonObjectRequest)


        }


    }
}

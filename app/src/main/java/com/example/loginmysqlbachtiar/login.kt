package com.example.loginmysqlbachtiar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var etUsername = findViewById<EditText>(R.id.et_username)
        var etPassword = findViewById<EditText>(R.id.et_password)
        var btnRegister = findViewById<Button>(R.id.btn_register)
        var btnLogin = findViewById<Button>(R.id.btn_login)

        val URL = "http://192.168.18.248/lgn/login.php"
        val jsonObject = JSONObject()
        val requestQueue = Volley.newRequestQueue(this)

        btnLogin.setOnClickListener {
            if (etUsername.text.toString().isEmpty()) {
                Toast.makeText(this, "Username harus diisi", Toast.LENGTH_SHORT).show()
            } else if (etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                jsonObject.put("username", etUsername.text.toString())
                jsonObject.put("password", etPassword.text.toString())

                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, URL, jsonObject, { response ->
                    if (response.getString("status") == "berhasil") {
                        Toast.makeText(
                            this,
                            "Anda Berhasil Login, ${response.getString("nama")}",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this,dashboard::class.java)
                        startActivity(intent)
                    } else if (response.getString("status") == "gagal") {
                        Toast.makeText(
                            this,
                            "Gagal Login, Username atau Password salah.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, { error ->
                    Toast.makeText(this, "Volley Error${error.toString()}", Toast.LENGTH_SHORT)
                        .show()
                })
                requestQueue.add(jsonObjectRequest)
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this,register::class.java)
            startActivity(intent)
        }
    }
}
package com.example.loginmysqlbachtiar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvStatus = findViewById<TextView>(R.id.tv_status)

        val URL = "http://192.168.10.66/lgn/koneksi.php"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,URL, null,
                Response.Listener { response ->
                    if (response.getString("status") == "berhasil") {
                        tvStatus.text = "Koneksi Berhasil"
                    } else if (response.getString("status") == "gagal") {
                        tvStatus.text = "Koneksi Gagal"
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Volley Error ${error.toString()}",Toast.LENGTH_SHORT).show()
                })
        requestQueue.add(jsonObjectRequest)
    }
}
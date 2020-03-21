package com.example.quizsqlite01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizsqlite01.model.DatabaseHandler
import kotlinx.android.synthetic.main.activity_masuk.*

class masuk : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        keRegistrasi.setOnClickListener{
            val intent = Intent(this, daftar::class.java)
            startActivity(intent)
        }

    }

    fun login(view: View) {
        val lgEmail = login_email!!.text.toString()
        val lgPassword = login_password!!.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

//        Check field terisi semua atau tidak
        if (lgEmail.trim() != "" && lgPassword.trim() != ""){

//            Check email ada tidak di databse table users
            if (databaseHandler!!.checkEmail(lgEmail.trim { it <= ' ' })) {

//                Check password sudah sama atau belum dengan email
                if (databaseHandler!!.userPresent(lgEmail.trim { it <= ' ' }, lgPassword.trim { it <= ' ' })) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("EMAIL", lgEmail.trim { it <= ' ' })
                    login_email.setText(null)
                    login_password.setText(null)
                    startActivity(intent)

                } else {
                    Toast.makeText(applicationContext, "Password anda salah", Toast.LENGTH_LONG).show()
                }

            } else{
                Toast.makeText(applicationContext, "Email anda salah", Toast.LENGTH_LONG).show()
            }

        } else{
            Toast.makeText(applicationContext,"Password dan Email anda salah", Toast.LENGTH_LONG).show()
        }
    }
}

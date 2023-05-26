package com.alex.injector.crashmanager

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alex.injector.databinding.CrashHandlerBinding

class CrashLoL : AppCompatActivity() {

    lateinit var  crashHandlerBinding: CrashHandlerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get a reference to the ActionBar
        val actionBar = supportActionBar
        // Set the color of the ActionBar
         actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1E1A1A")))
        window.apply {

            statusBarColor = Color.parseColor("#1E1A1A")
        }
        supportActionBar?.title = "Error reason "

        crashHandlerBinding  = CrashHandlerBinding.inflate(layoutInflater)

        setContentView(crashHandlerBinding.root)

        val value = intent.getStringExtra("logs")
        crashHandlerBinding.Crash.setText(value.toString())

    }

    fun alex (view: View) {
        try {
            Toast.makeText(this, "saved to /data/user/0/app/com.alex.injector.crashmanager/files/*.txt", Toast.LENGTH_SHORT)
                .show()
            startActivity( Intent ( Intent.ACTION_VIEW, Uri.parse("https://t.me/ALEX5402")))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        System.exit(1)
        super.onBackPressed()
    }
}

package com.example.keyinfinity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import java.util.Random

class MainActivity : AppCompatActivity() {

    private val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private lateinit var passwordTextView: TextView
    private val handler = Handler()
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        passwordTextView = findViewById(R.id.passwordTextView)

        val generateButton: Button = findViewById(R.id.generateButton)
        generateButton.setOnClickListener {
            val targetPassword = generateRandomPassword(20)
            startRandomLetterAnimation(targetPassword)
        }

        val copyButton: Button = findViewById(R.id.copyPassword)
        copyButton.setOnClickListener { copyToClipboard() }
    }

    private fun startRandomLetterAnimation(targetPassword: String) {
        var currentIndex = 0

        runnable = object : Runnable {
            override fun run() {
                val currentText = passwordTextView.text.toString()
                val newText = StringBuilder()

                if (currentIndex < targetPassword.length) {
                    newText.append(targetPassword.substring(0, currentIndex))
                } else {
                    newText.append(targetPassword)
                }



                val random = Random()
                for(i in currentIndex until targetPassword.length) {
                    newText.append(charset[random.nextInt(charset.size)])
                }

                passwordTextView.text = newText.toString()

                if(currentText == targetPassword) {
                    stopRandomLetterAnimation()
                } else {
                    currentIndex++
                    handler.postDelayed(this, 50)
                }
            }
        }

        handler.post(runnable)


    }

    private fun stopRandomLetterAnimation() {
        handler.removeCallbacks(runnable)
    }


    private fun generateRandomPassword(length: Int): String {
        val random = Random()
        val password = StringBuilder()

        repeat(length) {
            password.append(charset[random.nextInt(charset.size)])
        }

        return password.toString()
    }

    private fun copyToClipboard() {
        val password = passwordTextView.text.toString().trim()

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Password", password)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
    }
}
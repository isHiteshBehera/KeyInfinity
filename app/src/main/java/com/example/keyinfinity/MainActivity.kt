package com.example.keyinfinity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var passwordTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        passwordTextView = findViewById(R.id.passwordTextView)

        val generateButton: Button = findViewById(R.id.generateButton)
        generateButton.setOnClickListener { generatePassword() }

        val copyButton: Button = findViewById(R.id.copyPassword)
        copyButton.setOnClickListener { copyToClipboard() }
    }

    private fun generatePassword() {
        val password = generateRandomPassword(20)
        passwordTextView.text = password
    }

    private fun generateRandomPassword(length: Int): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val random = Random()
        val password = StringBuilder()

        repeat(length) {
            val index = random.nextInt(charset.size)
            password.append(charset[index])
        }

        return password.toString()
    }

    private fun copyToClipboard() {
        val password = passwordTextView.text.toString().trim()

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Password", password)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(this, "Cpoied", Toast.LENGTH_SHORT).show()
    }
}
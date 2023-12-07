package com.example.keyinfinity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
    }

    private fun generatePassword() {
        val password = generateRandomPassword(20)
        passwordTextView.text = "Your Password:\n$password"
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
}
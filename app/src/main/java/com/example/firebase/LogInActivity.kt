package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passworEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    private lateinit var nAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nAuth = FirebaseAuth.getInstance()
        if (nAuth.currentUser != null){
            gotoMain()
        }

        setContentView(R.layout.activity_login)
        this.init()
        this.registerListeners()

    }
    private fun init() {

        emailEditText = findViewById(R.id.editTextEmail)
        passworEditText  = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.LoginButton)
        registerButton = findViewById(R.id.RegisterButton)
    }
    private fun registerListeners() {
        loginButton.setOnClickListener{

            val email: String = emailEditText.text.toString()
            val pass: String = passworEditText.text.toString()

            if (email.isEmpty() || pass.isEmpty()){
                Toast.makeText( this,"Fields are Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            nAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        gotoMain()
                    }else{
                        Toast.makeText( this,"Error!", Toast.LENGTH_SHORT).show()
                    }
                }


        }

        registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))


        }


    }
    private fun gotoMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
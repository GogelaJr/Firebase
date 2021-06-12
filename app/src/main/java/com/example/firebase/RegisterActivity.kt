package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=\\S+$)" +  //no white spaces
                ".{9,}" +  //at least 4 characters
                "$"
    )
    private lateinit var RegisterEmail: EditText
    private lateinit var RegisterPassword: EditText
    private lateinit var RepeatPassword: EditText
    private lateinit var RegisterCompleteButton: Button
    private lateinit var mAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.init()
        this.registerListeners()

    }
    private fun init(){
        RegisterEmail = findViewById(R.id.RegisterEmail)
        RegisterPassword = findViewById(R.id.RegisterPassword)
        RepeatPassword = findViewById(R.id.RepeatPassword)
        RegisterCompleteButton = findViewById(R.id.RegisterCompleteButton)

        mAuth = FirebaseAuth.getInstance()



    }
    private fun registerListeners(){
        RegisterCompleteButton.setOnClickListener{
            val email: String = RegisterEmail.text.toString()
            val pass: String = RegisterPassword.text.toString()
            val repeat: String = RepeatPassword.text.toString()
            val a:String = "0"
            val b:String = "1"
            val c:String = "2"
            val d:String = "3"
            val e:String = "4"
            val f:String = "5"
            val g:String = "6"
            val h:String = "7"
            val i:String = "8"
            val k:String = "9"




            if (email.isEmpty() || pass.isEmpty() || repeat.isEmpty()){
                Toast.makeText(this, "Field is Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if(pass.length < 9){
                Toast.makeText(this,  "Password Should be minimum 9 symbols and contain atleast one number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass != repeat){
                Toast.makeText(this,"Password Doesnt Match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.contains(a) || pass.contains(b) || pass.contains(c) || pass.contains(d) || pass.contains(e) ||
                pass.contains(f) || pass.contains(g) || pass.contains(h) || pass.contains(i) || pass.contains(k) ){
                    mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            gotoMain()
                        }else{
                            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        }
    private fun gotoMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}
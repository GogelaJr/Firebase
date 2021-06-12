package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var newPass: EditText
    private lateinit var ChangeButton: Button
    private lateinit var mAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)
        this.init()
        this.registerListeners()
    }
    private fun init(){
        newPass = findViewById(R.id.ChangePassword)
        ChangeButton = findViewById(R.id.NewPasswordButton)
        mAuth = FirebaseAuth.getInstance()
    }
    private fun registerListeners(){
        ChangeButton.setOnClickListener{
            val newPass = newPass.text.toString()
            if (newPass.isEmpty() || newPass.length < 9){
                Toast.makeText(this, "Field Empty or Less than 9 Symbols", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.currentUser?.updatePassword(newPass)?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
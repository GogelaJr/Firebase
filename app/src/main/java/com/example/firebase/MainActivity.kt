package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.*
import org.w3c.dom.Text



class MainActivity : AppCompatActivity() {

    private lateinit var PassChangeActivity: Button
    private lateinit var Logout: Button
    private lateinit var Name: TextView
    private lateinit var Last: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var Firstname: EditText
    private lateinit var Lastname: EditText
    private lateinit var SaveButton: Button
    private lateinit var db: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.init()
        this.registerListeners()


    }
    private fun init(){
        PassChangeActivity = findViewById(R.id.PassChangeButton)
        Logout = findViewById(R.id.LogoutButton)
        Name = findViewById(R.id.FirstnameText)
        Last = findViewById(R.id.LastnameText)
        Firstname = findViewById(R.id.EditFirstname)
        Lastname = findViewById(R.id.EditLastname)
        SaveButton = findViewById(R.id.SaveNameButton)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")
    }
    private fun registerListeners(){
        PassChangeActivity.setOnClickListener{
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }
        Logout.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
            mAuth.signOut()
            finish()
        }
        SaveButton.setOnClickListener{
            val name: String = Firstname.text.toString()
            val lastname: String = Lastname.text.toString()
            if(name.isEmpty() || lastname.isEmpty()){
                Toast.makeText(this, "Field is Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                Name.setText(name)
                Last.setText(lastname)
            }
        }
    }
}
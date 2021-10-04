package com.example.squidcov19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class REGISTROActivity : AppCompatActivity() {


    private lateinit var  txtnameperson:EditText
    private lateinit var  password1:EditText
    private lateinit var  emailaddres:EditText
    private lateinit var  dnitxt:EditText
    private lateinit var  progressBar:ProgressBar
    private lateinit var  dbRefences:DataBaseReference
    private lateinit var  database:FirebaseDataBase
    private lateinit var  auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registroactivity)
        txtnameperson = findViewById(R.id.txtnameperson)
        password1 = findViewById(R.id.password1)
        emailaddres = findViewById(R.id.emailaddres)
        dnitxt = findViewById(R.id.dnitxt)

        progressBar = ProgressBar( context:this )

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbRefences = database.reference.child("User")
        }

    fun register(view:View){
            createnewAccount()
        }

    private fun createnewAccount(){
            val name:String=txtnameperson.text.toString()
            val password:String= password1.text.toString()
            val email:String = emailaddres.text.toString()
            val dni:String = dnitxt.text.toString()

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(dni)){
                    progressBar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task ->

                        if(task.isComplete){
                           val user:FirebaseUser?=auth.currentUser
                            verifyemail(user)
                            val userBD= dbRefences.child(user?.uid)
                            userBD.child("name").setValue(name)
                            userBD.child("password").setValue(password)
                            action()
                        }

                    }

            }
        }
    private fun action(){
        startActivity(intent(this,LOGINACTIVITY1::class.java))
    }
    }



    private fun verifyemail(user:FirebaseUser?){
        user.sendEmailVerification()
            .addOnCompleteListener(this){
                task->

                if(task.isComplete){
                    Toast.makeText( context: this, text:"email enviado", Toast.LENGHT_LOW).show()
                }
                else{
                    Toast.makeText(context: this, text:"ERROR", Toast.LENGHT_LOW).show()
                }
            }

    }


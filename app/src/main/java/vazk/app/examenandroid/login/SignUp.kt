package vazk.app.examenandroid.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import vazk.app.examenandroid.R
import vazk.app.examenandroid.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()


        binding.button.setOnClickListener{

            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if (pass == confirmPass)
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this,SignIn::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()

                        }
                    }

                }else{
                    Toast.makeText(this,"La contraseña no es la misma",Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this,"Campo vacio!!",Toast.LENGTH_SHORT).show()

            }
        }

    }
}
package vazk.app.examenandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import vazk.app.examenandroid.colaboladores.Colaboladores
import vazk.app.examenandroid.colaboladores.mapa.MapsActivity
import vazk.app.examenandroid.colaboladores.mapa.model.LocationModel
import vazk.app.examenandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnColabo.setOnClickListener{
            val intent = Intent(this, Colaboladores::class.java)
            startActivity(intent)
        }


        binding.btnAgregar.setOnClickListener{
            Toast.makeText(this,"Lamentablemente falto tiempo para implementar esta función. \nGRACIAS!!", Toast.LENGTH_LONG).show()
        }

    }
}
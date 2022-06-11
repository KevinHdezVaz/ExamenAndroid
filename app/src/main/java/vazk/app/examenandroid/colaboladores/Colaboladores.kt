package vazk.app.examenandroid.colaboladores

import UserAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_colaboladores.*
import kotlinx.android.synthetic.main.activity_lista_colab.*
import org.json.JSONException
import vazk.app.examenandroid.R
import vazk.app.examenandroid.colaboladores.mapa.MapsActivity
import vazk.app.examenandroid.databinding.ActivityColaboladoresBinding
import vazk.app.examplejson.Model.Users
import java.io.IOException
import java.nio.charset.Charset


class Colaboladores : AppCompatActivity()  {
private lateinit var binding: ActivityColaboladoresBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColaboladoresBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.maps.setOnClickListener {


            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }





        try {
            val jsonString = getJSONFromAssets()!!
            val users = Gson().fromJson(jsonString, Users::class.java)

            rvUsersList.layoutManager = LinearLayoutManager(this)
            val itemAdapter = UserAdapter(this, users.data)
            rvUsersList.adapter = itemAdapter
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    public fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("User.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)


        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


}
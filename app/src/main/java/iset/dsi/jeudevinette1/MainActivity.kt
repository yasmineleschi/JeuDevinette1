package iset.dsi.jeudevinette1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var btne :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        btne = findViewById(R.id.btn2)

        btn.setOnClickListener{
            val intent = Intent(this, DebutantActivity::class.java)

            startActivity(intent)
        }
        btne.setOnClickListener {
            val i = Intent( this , ExpertActivity::class.java)

            startActivity(i)
        }




    }
}
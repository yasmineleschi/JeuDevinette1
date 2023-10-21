package iset.dsi.jeudevinette1

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class DebutantActivity : AppCompatActivity()
{
    lateinit var guess : Button
    lateinit var e : EditText
    lateinit var  refresh : Button
    lateinit var t : TextView
    lateinit var th : TextView
    lateinit var showHistoryButton: ImageButton
    var random: Int = nextInt( 1, 100)
    val guessHistory = mutableListOf<Int>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debutant)
        guess = findViewById(R.id.guess)
        e = findViewById(R.id.e)
        refresh = findViewById(R.id.refresh)
        t = findViewById(R.id.t)
        th = findViewById(R.id.h)
        showHistoryButton = findViewById(R.id.history)
        t.text = "Guessing Game"

        guess.setOnClickListener {
            val num:Int = e.text.toString().toInt()
            if (num < random){
                t.setTextColor(resources.getColor(R.color.jeune))
                t.text = "Wrong,your number is low ! "
                e.text.clear()
            }else if (num > random) {
                t.setTextColor(resources.getColor(R.color.rouge))
                t.text = "Wrong, your number is high! "
                e.text.clear()
            } else{
                t.setTextColor(resources.getColor(R.color.vert))
                t.text = "Congratulation , your number is right "
                e.text.clear()
            }
            guessHistory.add(num)


        }
        refresh.setOnClickListener {
            refresh()

        }
        showHistoryButton.setOnClickListener {
            showGuessHistory()
        }

    }
    fun refresh (){
        random = nextInt(1 ,100)
        e.text.clear()
    }

    private fun showMessage(message: String, colorResource: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()


        dialog.findViewById<TextView>(android.R.id.message)?.setTextColor(
            resources.getColor(colorResource)
        )
    }
    private fun showGuessHistory() {
        val historyText = guessHistory.joinToString(", ")
        showMessage(historyText, android.R.color.black)
    }

}
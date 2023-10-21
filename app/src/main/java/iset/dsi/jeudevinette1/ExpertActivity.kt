package iset.dsi.jeudevinette1


import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import android.content.Intent
import android.os.CountDownTimer


class ExpertActivity : AppCompatActivity() {

    lateinit var g : Button
    lateinit var ed : EditText
    lateinit var  re : Button
    lateinit var t : TextView
    lateinit var time : TextView
    private var countDownTimer: CountDownTimer? = null
    private var secondsRemaining: Long = 20000 / 1000
    var random: Int = Random.nextInt(1, 100)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert)

        g = findViewById(R.id.guess1)
        ed = findViewById(R.id.edit)
        re = findViewById(R.id.refresh1)
        t = findViewById(R.id.text)
        time = findViewById(R.id.timerTextView)
        t.text = "Guessing Game"

        g.setOnClickListener {
            val num:Int = ed.text.toString().toInt()
            if (num < random){
                t.setTextColor(resources.getColor(R.color.jeune))
                t.text = "Wrong,your number is low ! "
                ed.text.clear()
            }else if (num > random) {
                t.setTextColor(resources.getColor(R.color.rouge))
                t.text = "Wrong, your number is high! "
                ed.text.clear()
            } else{
                t.setTextColor(resources.getColor(R.color.vert))
                t.text = "Congratulation , your number is right "
                ed.text.clear()
            }

            countDownTimer?.cancel()
            startTimer()

        }
        re.setOnClickListener {
            refresh()

        }

    }
    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateTimerTextView()
            }

            override fun onFinish() {

                refresh()
                showMessage("Time's up! Please make a new guess.",R.color.rouge)
                startTimer()
            }
        }.start()
    }
    private fun updateTimerTextView() {
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        val timerText = String.format("%02d:%02d", minutes, seconds)
        time.text = timerText
    }

    private fun showMessage(message: String, colorResource: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        val dialog = builder.create()
        dialog.show()


        dialog.findViewById<TextView>(android.R.id.message)?.setTextColor(
            resources.getColor(colorResource)
        )

    }
    fun refresh (){
        random = Random.nextInt(1, 100)
        ed.text.clear()
    }

}
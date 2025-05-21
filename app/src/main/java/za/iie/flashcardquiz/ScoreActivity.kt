package za.iie.flashcardquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    private lateinit var txtScore: TextView
    private lateinit var txtMessage: TextView
    private lateinit var btnExit: Button
    private lateinit var btnReview: Button
    private lateinit var reviewText: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        txtScore = findViewById(R.id.txtScore)
        txtMessage = findViewById(R.id.txtMessage)
        btnExit = findViewById(R.id.btnExit)
        btnReview = findViewById(R.id.btnReview)
        reviewText = findViewById(R.id.txtReview)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        txtScore.text = "You got $score out of 5"

        txtMessage.text = if (score >= 3) "Great job!" else "Keep practicing!"

        btnReview.setOnClickListener {
            if (questions != null && answers != null) {
                val reviewBuilder = StringBuilder()
                for (i in questions.indices) {
                    reviewBuilder.append("${i + 1}. ${questions[i]} → Answer: ${answers[i]}\n\n")
                }
                reviewText.text = reviewBuilder.toString()
            }
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}

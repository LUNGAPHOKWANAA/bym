package za.iie.flashcardquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class QuizActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "The Great Wall of China is located in Japan.",
        "The Berlin Wall fell in 1989.",
        "Julius Caesar was the first emperor of Rome.",
        "The Cold War ended in 1991."
    )

    private val answers = arrayOf(true, false, true, false, true)

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.txtQuestion)
        feedbackTextView = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }
        btnNext.setOnClickListener { nextQuestion() }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            questionTextView.text = questions[currentQuestionIndex]
            feedbackTextView.text = ""
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentQuestionIndex]
        if (userAnswer == correctAnswer) {
            feedbackTextView.text = "Correct!"
            score++
        } else {
            feedbackTextView.text = "Incorrect!"
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
            loadQuestion()
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            startActivity(intent)
            finish()
        }
    }
}


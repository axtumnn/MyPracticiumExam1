package za.ac.iie.mypracticumexam1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {

    // Declare arrays to hold the received data
    private lateinit var songs: ArrayList<String>
    private lateinit var artists: ArrayList<String>
    private lateinit var ratings: ArrayList<Int>
    private lateinit var comments: ArrayList<String>
    private lateinit var artistInfo: ArrayList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)

        // Retrieve arrays from Intent
        songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
        artistInfo = intent.getStringArrayListExtra("artistInfo") ?: arrayListOf()

        // UI references
        val displayText = findViewById<TextView>(R.id.txtDisplay)
        val displayBtn = findViewById<Button>(R.id.btnDisplay)
        val avgBtn = findViewById<Button>(R.id.btnRating)
        val backBtn = findViewById<Button>(R.id.btnBack)

        // Display all songs with full info
        displayBtn.setOnClickListener {
            if (songs.isEmpty()) {
                displayText.text = "No songs in the playlist."
                return@setOnClickListener
            }

            val builder = StringBuilder()
            for (i in songs.indices) {
                builder.append("${i + 1}. ${songs[i]}\n")
                builder.append("Rating: ${ratings[i]}\n")
                builder.append("Comment: ${comments[i]}\n")
                builder.append("Artist Info: ${artistInfo[i]}\n\n")
            }
            displayText.text = builder.toString()
        }

        // Calculate and display average rating
        avgBtn.setOnClickListener {
            if (ratings.isEmpty()) {
                displayText.text = "No ratings available."
                return@setOnClickListener
            }

            val total = ratings.sum()
            val average = total.toDouble() / ratings.size
            displayText.text = "Average Rating: %.2f".format(average)
        }

        // Return to MainActivity
        backBtn.setOnClickListener {
            finish()
        }






















        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
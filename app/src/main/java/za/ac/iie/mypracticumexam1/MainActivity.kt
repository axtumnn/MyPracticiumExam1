package za.ac.iie.mypracticumexam1
// STUDENT NUMBER: 10488245
// FULL NAME: Matthew Raegan Ethan Reyneke

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import android.util.Log
import android.content.Intent


class MainActivity : AppCompatActivity() {

            // Parallel arrays to store song data
            private val songs = arrayListOf<String>()
            private val artists = arrayListOf<String>()
            private val ratings = arrayListOf<Int>()
            private val comments = arrayListOf<String>()
            private val artistInfo = arrayListOf<String>() // NEW array for extended artist info

            @SuppressLint("MissingInflatedId")
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContentView(R.layout.activity_main)

                //  input fields
                val songInput = findViewById<EditText>(R.id.edtSong)
                val artistInput = findViewById<EditText>(R.id.edtArtist)
                val ratingInput = findViewById<EditText>(R.id.edtRating)
                val commentInput = findViewById<EditText>(R.id.edtComment)
                val artistInfoInput = findViewById<EditText>(R.id.edtArtistInfo)

                //  buttons
                val addBtn = findViewById<Button>(R.id.btnAdd)
                val detailBtn = findViewById<Button>(R.id.btnDetail)
                val exitBtn = findViewById<Button>(R.id.btnExit)

                // "Add to Playlist" functionality
                addBtn.setOnClickListener {
                    try {
                        val song = songInput.text.toString()
                        val artist = artistInput.text.toString()
                        val ratingText = ratingInput.text.toString()
                        val comment = commentInput.text.toString()
                        val info = artistInfoInput.text.toString()

                        // Validation
                        if (song.isBlank() || artist.isBlank() || ratingText.isBlank() || comment.isBlank() || info.isBlank()) {
                            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }

                        val rating = ratingText.toInt()
                        if (rating !in 1..5) {
                            Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }

                        // Limit to 4 songs
                        if (songs.size >= 4) {
                            Toast.makeText(this, "Only 4 songs allowed for this demo.", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }

                        // Add to arrays
                        songs.add(song)
                        artists.add(artist)
                        ratings.add(rating)
                        comments.add(comment)
                        artistInfo.add(info)

                        // Confirmation + log
                        Toast.makeText(this, "Song added to playlist!", Toast.LENGTH_SHORT).show()
                        Log.i("Playlist", "Added: $song by $artist ($rating) - $comment | Info: $info")

                        // Clear input fields
                        songInput.text.clear()
                        artistInput.text.clear()
                        ratingInput.text.clear()
                        commentInput.text.clear()
                        artistInfoInput.text.clear()

                    } catch (e: Exception) {
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                // Navigate to detailed view screen
                detailBtn.setOnClickListener {
                    val intent = Intent(this, DetailedViewScreen::class.java).apply {
                        putStringArrayListExtra("songs", songs)
                        putStringArrayListExtra("artists", artists)
                        putIntegerArrayListExtra("ratings", ArrayList(ratings)) // FIXED HERE
                        putStringArrayListExtra("comments", comments)
                        putStringArrayListExtra("artistInfo", artistInfo)
                    }
                    startActivity(intent)
                }

                // Exit the app
                exitBtn.setOnClickListener {
                    finishAffinity()
                }
            }
        }


























package vcmsa.ci.game

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Input : AppCompatActivity() {

    private lateinit var dateInput: EditText
    private lateinit var minutesPlayedInput: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var ratingSpinner: Spinner
    private lateinit var addEntryButton: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDetail: Button

    private val dates = mutableListOf<String>()
    private val minutesPlayedList = mutableListOf<Int>()
    private val genres = mutableListOf<String>()
    private val ratings = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input)

        dateInput = findViewById(R.id.dateinputeditText)
        minutesPlayedInput = findViewById(R.id.minsplayededitText)
        genreSpinner = findViewById(R.id.gameGenre)
        ratingSpinner = findViewById(R.id.gameRatingSpinner) // Updated for Spinner
        addEntryButton = findViewById(R.id.Addentrybtn)
        buttonClear = findViewById(R.id.buttonClear)
        buttonDetail = findViewById(R.id.buttonDetail)

        addEntryButton.setOnClickListener { addGameEntry() }
        buttonClear.setOnClickListener { clearInputs() }
        buttonDetail.setOnClickListener { startActivity(Intent(this, DetailedViewScreen::class.java)) }

        // Ensure proper edge-to-edge layout adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView)) { view, insets ->
            view.setPadding(0, insets.systemWindowInsetTop, 0, insets.systemWindowInsetBottom)
            insets
        }
    }

    private fun addGameEntry() {
        try {
            val date = dateInput.text.toString().trim()
            if (date.isEmpty()) throw IllegalArgumentException("Date cannot be empty!")

            val minutesPlayed = minutesPlayedInput.text.toString().trim().toInt()
            val genre = genreSpinner.selectedItem.toString()
            val rating = ratingSpinner.selectedItem.toString().toInt() // Updated for Spinner

            dates.add(date)
            minutesPlayedList.add(minutesPlayed)
            genres.add(genre)
            ratings.add(rating)

            Toast.makeText(this, "Entry Added!", Toast.LENGTH_SHORT).show()

            // Debugging log to verify stored entries
            dates.indices.forEach { i ->
                println("Game session: Date: ${dates[i]}, Minutes: ${minutesPlayedList[i]}, Genre: ${genres[i]}, Rating: ${ratings[i]}")
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format for minutes or rating", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputs() {
        dateInput.text.clear()
        minutesPlayedInput.text.clear()
        genreSpinner.setSelection(0)
        ratingSpinner.setSelection(0) // Updated for Spinner
    }
}
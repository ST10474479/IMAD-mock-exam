package vcmsa.ci.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Input : AppCompatActivity() {

    // Declare UI elements
    private lateinit var dateInput: EditText
    private lateinit var minutesPlayedInput: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var ratingGroup: RadioGroup
    private lateinit var addEntryButton: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDetail: Button

    // Store data in lists
    private val dates = mutableListOf<String>()
    private val minutesPlayedList = mutableListOf<Int>()
    private val genres = mutableListOf<String>()
    private val ratings = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input)
        // Initialize UI elements
        dateInput = findViewById(R.id.dateinputeditText)
        minutesPlayedInput = findViewById(R.id.minsplayededitText)
        genreSpinner = findViewById(R.id.gameGenre)
        ratingGroup = findViewById(R.id.Gamerating)
        addEntryButton = findViewById(R.id.Addentrybtn)
        buttonClear = findViewById(R.id.buttonClear)
        buttonDetail = findViewById(R.id.buttonDetail)

        // Button actions
        addEntryButton.setOnClickListener {
            addGameEntry()
        }

        buttonClear.setOnClickListener {
            clearInputs()
        }

        buttonDetail.setOnClickListener {
            startActivity(Intent(this, DetailedViewScreen::class.java))
        }
    }

    private fun addGameEntry() {
        try {
            val date = dateInput.text.toString()
            val minutesPlayed = minutesPlayedInput.text.toString().toInt()
            val genre = genreSpinner.selectedItem.toString()

            // Get selected radio button value
            val selectedRatingId = ratingGroup.checkedRadioButtonId
            if (selectedRatingId == -1) {
                throw IllegalArgumentException("Please select a rating")
            }
            val rating = findViewById<RadioButton>(selectedRatingId).text.toString().toInt()

            // Store data in lists
            dates.add(date)
            minutesPlayedList.add(minutesPlayed)
            genres.add(genre)
            ratings.add(rating)

            Toast.makeText(this, "Entry Added!", Toast.LENGTH_SHORT).show()

            // Display stored entries
            for (i in dates.indices) {
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
        ratingGroup.clearCheck()
    }
}
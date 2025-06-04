package vcmsa.ci.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnStart.setOnClickListener {

            //Connects this screen to the next
            val intent = Intent(this, Input::class.java)
            startActivity(intent)
        }
        btnExit.setOnClickListener {
            //closes the current activity, removing it from the apps navigation stack
            finishAffinity()
            //terminates the application, the 0 indicates a normal exit with no errors
            exitProcess(0)
        }
    }
}



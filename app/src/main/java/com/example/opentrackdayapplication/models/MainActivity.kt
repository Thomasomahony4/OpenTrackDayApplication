package com.example.opentrackdayapplication.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.opentrackdayapplication.R
import com.google.android.material.switchmaterial.SwitchMaterial
import timber.log.Timber
import java.util.Objects

// MainActivity is the main screen of the application.
class MainActivity : AppCompatActivity() {

    // Called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display so the app can draw behind system bars.
        enableEdgeToEdge()

        // Loads the layout for the main activity.
        setContentView(R.layout.activity_main)

        // Sets up Timber for logging debug messages during development.
        Timber.plant(Timber.DebugTree())

        // Applies padding to the main view to account for system bars.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            // Gets the sizes of the system bars, such as the status bar and navigation bar.
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Adds padding so the content does not overlap the system bars.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Returns the insets so they can continue through the view system.
            insets
        }

        // Finds the switch used to change the application's theme.
        val switchBtn = findViewById<SwitchMaterial?>(R.id.switchBtn)

        // Listens for changes to the theme switch.
        switchBtn.setOnCheckedChangeListener { Null, isChecked ->

            // Checks whether the switch is turned on.
            if (isChecked) {
                // Sets the application theme to dark mode.
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Sets the application theme to light mode.
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Finds the button used to display the list of saved marks.
        val viewMarksButton =
            findViewById<Button>(R.id.viewMarksButton)

        // Finds the button used to add a new mark.
        val addMarkButton =
            findViewById<Button>(R.id.addMarkButton)

        // Sets an action for when the user clicks the View Marks button.
        viewMarksButton.setOnClickListener {
            // Opens MarkListActivity to display the saved placemarks.
            startActivity(
                Intent(this, MarkListActivity::class.java)
            )
        }

        // Sets an action for when the user clicks the Add Mark button.
        addMarkButton.setOnClickListener {
            // Opens AddEditActivity to create a new placemark.
            startActivity(
                Intent(this, AddEditActivity::class.java)
            )
        }
    }
}
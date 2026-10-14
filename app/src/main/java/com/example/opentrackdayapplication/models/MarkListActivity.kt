package com.example.opentrackdayapplication.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opentrackdayapplication.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import timber.log.Timber

class MarkListActivity : AppCompatActivity() {

    // Declares the RecyclerView used to display the list of marks.
    private lateinit var recyclerView: RecyclerView

    // Declares the adapter that manages the marks displayed in the RecyclerView.
    private lateinit var adapter: PlacedMarkAdapter

    // Called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets the layout file for the mark list screen.
        setContentView(R.layout.activity_mark_list)

        // Finds the RecyclerView in the layout using its ID.
        recyclerView =
            findViewById(R.id.marksRecyclerView)

        // Creates the adapter and supplies it with the existing placed marks.
        adapter = PlacedMarkAdapter(

            marks = AppData.placedMarks.findAll(),

            // Defines what happens when a mark is clicked or long-clicked.
            listener = object : PlacedMarkAdapter.OnMarkListener {

                // Called when the user clicks a mark to edit it.
                override fun onMarkClick(mark: PlacemarkModel) {
                    // Logs the ID and title of the mark being edited.
                    Timber.d("Editing mark id=${mark.id}, title=${mark.title}")

                    // Opens the screen for editing the selected mark.
                    editMark(mark)
                }

                // Called when the user long-clicks a mark to delete it.
                override fun onMarkLongClick(mark: PlacemarkModel): Boolean {
                    // Logs the ID and title of the mark being deleted.
                    Timber.d("Deleting mark id=${mark.id}, title=${mark.title}")

                    // Deletes the selected mark and updates the list.
                    deleteMark(mark)

                    // Returns true to indicate that the long-click was handled.
                    return true
                }
            },
        )

        // Arranges the marks vertically in the RecyclerView.
        recyclerView.layoutManager =
            LinearLayoutManager(this)

        // Connects the adapter to the RecyclerView so the marks can be displayed.
        recyclerView.adapter = adapter

        // Finds the button that returns the user to the main screen.
        val returnButton =
            findViewById<Button>(R.id.returnButton)

        // Sets an action to perform when the return button is clicked.
        returnButton.setOnClickListener {
            // Opens MainActivity when the user presses the return button.
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    // Called when the activity becomes visible again.
    override fun onResume() {
        super.onResume()

        // Checks that the adapter has been initialized before using it.
        if (::adapter.isInitialized) {

            // Refreshes the displayed marks using the latest stored data.
            adapter.updateMarks(
                AppData.placedMarks.findAll()
            )
        }
    }

    // Opens the AddEditActivity screen to edit the selected mark.
    private fun editMark(mark: PlacemarkModel) {

        // Creates an intent to open the add/edit activity.
        val intent =
            Intent(this, AddEditActivity::class.java)

        // Passes the selected mark's ID to the activity.
        intent.putExtra("id", mark.id)

        // Starts the activity with the selected mark's ID.
        startActivity(intent)
    }

    // Deletes the selected mark from the stored collection.
    private fun deleteMark(mark: PlacemarkModel) {

        // Removes the mark from the data source using its ID.
        AppData.placedMarks.delete(mark.id)

        // Refreshes the RecyclerView to show the updated list.
        adapter.updateMarks(
            AppData.placedMarks.findAll()
        )
    }
}
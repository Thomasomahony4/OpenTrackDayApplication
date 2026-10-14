package com.example.opentrackdayapplication.models


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opentrackdayapplication.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

// Adapter that connects the list of placemarks to the RecyclerView.
class PlacedMarkAdapter(
    // Holds the list of placemarks that will be displayed.
    private var marks: List<PlacemarkModel>,

    // Handles clicks and long-clicks on individual placemarks.
    private val listener: OnMarkListener,
) : RecyclerView.Adapter<PlacedMarkAdapter.MarkViewHolder>() {

    // Holds references to the views used to display a single placemark.
    class MarkViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        // Displays the ID and title of the placemark.
        val titleText: TextView =
            itemView.findViewById(R.id.titleText)

        // Displays the placemark's description.
        val descriptionText: TextView =
            itemView.findViewById(R.id.descriptionText)

        // Displays the X and Y coordinates of the placemark.
        val coordinatesText: TextView =
            itemView.findViewById(R.id.coordinatesText)

        // Displays the category of the placemark.
        val categoryText: TextView =
            itemView.findViewById(R.id.categoryText)
    }

    // Creates a ViewHolder whenever the RecyclerView needs a new list item view.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarkViewHolder {

        // Inflates the layout used for an individual placemark item.
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_placed_mark,
                parent,
                false
            )

        // Returns a ViewHolder containing the inflated layout.
        return MarkViewHolder(view)
    }

    // Connects the placemark data at the specified position to its views.
    override fun onBindViewHolder(
        holder: MarkViewHolder,
        position: Int
    ) {

        // Gets the placemark corresponding to the current list position.
        val mark = marks[position]

        // Displays the placemark's ID and title.
        holder.titleText.text =
            "${mark.id}: ${mark.title}"

        // Displays the placemark's description.
        holder.descriptionText.text =
            mark.desc

        // Displays the placemark's X and Y coordinates.
        holder.coordinatesText.text =
            "X: ${mark.x}, Y: ${mark.y}"

        // Displays the placemark's category.
        holder.categoryText.text =
            mark.category

        // Sets an action for when the user clicks a placemark.
        holder.itemView.setOnClickListener {
            // Passes the selected placemark to the click listener.
            listener.onMarkClick(mark)

        }

        // Sets an action for when the user long-clicks a placemark.
        holder.itemView.setOnLongClickListener {
            // Passes the selected placemark to the long-click listener.
            // Returns the Boolean result to indicate whether the event was handled.
            listener.onMarkLongClick(mark)

        }
    }

    // Defines the functions that handle placemark click events.
    interface OnMarkListener {

        // Called when a placemark is clicked, typically to edit it.
        fun onMarkClick(mark: PlacemarkModel)

        // Called when a placemark is long-clicked, typically to delete it.
        // Returns true if the long-click event has been handled.
        fun onMarkLongClick(mark: PlacemarkModel): Boolean

    }

    // Returns the total number of placemarks in the list.
    override fun getItemCount(): Int {
        return marks.size
    }

    // Replaces the current list with updated placemark data.
    fun updateMarks(newMarks: List<PlacemarkModel>) {
        // Stores the new list of placemarks.
        marks = newMarks

        // Notifies the RecyclerView that the data has changed and the list needs refreshing.
        notifyDataSetChanged()
    }

}
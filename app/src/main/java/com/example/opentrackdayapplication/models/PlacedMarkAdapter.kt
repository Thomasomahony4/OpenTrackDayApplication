package com.example.opentrackdayapplication.models


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opentrackdayapplication.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class PlacedMarkAdapter(
    private var marks: List<PlacemarkModel>,
    private val listener: OnMarkListener,
) : RecyclerView.Adapter<PlacedMarkAdapter.MarkViewHolder>() {

    class MarkViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val titleText: TextView =
            itemView.findViewById(R.id.titleText)

        val descriptionText: TextView =
            itemView.findViewById(R.id.descriptionText)

        val coordinatesText: TextView =
            itemView.findViewById(R.id.coordinatesText)

        val categoryText: TextView =
            itemView.findViewById(R.id.categoryText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarkViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_placed_mark,
                parent,
                false
            )

        return MarkViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MarkViewHolder,
        position: Int
    ) {

        val mark = marks[position]

        holder.titleText.text =
            "${mark.id}: ${mark.title}"

        holder.descriptionText.text =
            mark.desc

        holder.coordinatesText.text =
            "X: ${mark.x}, Y: ${mark.y}"

        holder.categoryText.text =
            mark.category

        holder.itemView.setOnClickListener {

            listener.onMarkClick(mark)

        }

        holder.itemView.setOnLongClickListener {

            listener.onMarkLongClick(mark)

        }
        }

    interface OnMarkListener {

        fun onMarkClick(mark: PlacemarkModel)

        fun onMarkLongClick(mark: PlacemarkModel): Boolean

    }

    override fun getItemCount(): Int {
        return marks.size
    }

    fun updateMarks(newMarks: List<PlacemarkModel>) {
        marks = newMarks
        notifyDataSetChanged()
    }

}
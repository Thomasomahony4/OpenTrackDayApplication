package com.example.opentrackdayapplication.models

import java.util.concurrent.atomic.AtomicLong

class PlacemarkStore {

    // Stores all placemarks in an ArrayList.
    private val placemarks = ArrayList<PlacemarkModel>()

    // Generates a unique, increasing ID for each new placemark.
    private val lastId = AtomicLong(0L)

    // Returns a list containing all stored placemarks.
    fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    // Adds a new placemark to the store.
    fun create(placemark: PlacemarkModel) {
        // Assigns the placemark a new unique ID.
        placemark.id = lastId.incrementAndGet()

        // Adds the placemark to the collection.
        placemarks.add(placemark)
    }

    // Updates the details of an existing placemark.
    // Returns true if the placemark is found and updated, or false otherwise.
    fun update(placemark: PlacemarkModel): Boolean {
        // Searches for the placemark using its ID.
        val foundPlacemark = findOne(placemark.id)

        return if (foundPlacemark != null) {
            // Updates the title of the existing placemark.
            foundPlacemark.title = placemark.title

            // Updates the description of the existing placemark.
            foundPlacemark.desc = placemark.desc

            // Updates the x-coordinate of the placemark.
            foundPlacemark.x = placemark.x

            // Updates the y-coordinate of the placemark.
            foundPlacemark.y = placemark.y

            // Indicates that the update was successful.
            true
        } else {
            // Indicates that no matching placemark was found.
            false
        }
    }

    // Deletes a placemark using its ID.
    // Returns true if the placemark is found and deleted, or false otherwise.
    fun delete(id: Long): Boolean {
        // Searches for the placemark with the specified ID.
        val foundPlacemark = findOne(id)

        return if (foundPlacemark != null) {
            // Removes the matching placemark from the collection.
            placemarks.remove(foundPlacemark)

            // Indicates that the deletion was successful.
            true
        } else {
            // Indicates that no matching placemark was found.
            false
        }
    }

    // Finds and returns a placemark with the specified ID.
    // Returns null if no matching placemark exists.
    fun findOne(id: Long): PlacemarkModel? {
        // Searches the collection for the first placemark with a matching ID.
        return placemarks.find { p -> p.id == id }
    }

    // Returns all placemarks that match the specified category filter.
    fun findFiltered(filter: String): ArrayList<PlacemarkModel> {
        // Creates a new list to hold the matching placemarks.
        val filterMarks = ArrayList<PlacemarkModel>()

        // Checks each stored placemark.
        for (mark in placemarks){

            // Compares the filter string with the placemark's category.
            if (filter.contentEquals(mark.category)){

                // Adds the placemark to the results if the categories match.
                filterMarks.add(mark)
            }
        }

        // Returns the list of matching placemarks.
        return filterMarks
    }
}
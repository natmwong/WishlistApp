package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val wishlistItems: ArrayList<Item> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText and Button declarations for wishlist item
        val nameEt = findViewById<EditText>(R.id.itemName)
        val priceEt = findViewById<EditText>(R.id.itemPrice)
        val urlEt = findViewById<EditText>(R.id.itemURL)
        val button = findViewById<Button>(R.id.submitButton)

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRv)

        // Create adapter passing in the list of emails
        val adapter = ItemAdapter(this, wishlistItems)
        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter
        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)

        // Retrieves name, price, and the url from the user to create an item and add it to the wishlist
        fun addItemToWishlist() {
            val name = nameEt.text.toString()
            val price = priceEt.text.toString()
            val url = urlEt.text.toString()

            if (name.isNotBlank() && price.isNotBlank() && url.isNotBlank()) {
                val newItem = Item(name, price, url)
                wishlistItems.add(newItem)
                adapter.notifyDataSetChanged()

                // Clear input fields after adding item
                nameEt.text.clear()
                priceEt.text.clear()
                urlEt.text.clear()
            } else {
                // Show a message to the user to fill in all fields
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // On button click, add item to wishlist
        button.setOnClickListener {
            addItemToWishlist()
        }
    }
}
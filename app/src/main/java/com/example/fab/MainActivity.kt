package com.example.fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var listItem = ArrayList<String>()
    var adapter : ArrayAdapter<String>? = null

    lateinit var listView: ListView
    lateinit var fab: FloatingActionButton
    lateinit var undoOnClickListener: View.OnClickListener
    var itemCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        listView.adapter = adapter
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            addListItem()
            Snackbar.make(it, "Item Added", Snackbar.LENGTH_SHORT).setAction("Undo", undoOnClickListener).show()
        }
        undoOnClickListener = View.OnClickListener {
            listItem.removeAt(listItem.size - 1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item Removed", Snackbar.LENGTH_SHORT).setAction("Message", null).show()
            itemCount -= 1
        }
    }

    private fun addListItem() {
        listItem.add("Item $itemCount")
        adapter?.notifyDataSetChanged()
        itemCount += 1
    }
}
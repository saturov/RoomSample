package ru.rebrain.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.rebrain.room.ui.adapter.BookListAdapter
import ru.rebrain.room.vm.BooksViewModel
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val newBookActivityRequestCode = 1
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        booksViewModel.allBooks.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setBooks(it) }
        })

        val btn = findViewById<Button>(R.id.add_btn)
        btn.setOnClickListener {
            val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }

    }
}

package ru.rebrain.room.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.rebrain.room.db.BooksRoomDatabase
import ru.rebrain.room.model.Book
import ru.rebrain.room.repository.BooksRepository
import kotlin.coroutines.CoroutineContext

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: BooksRepository
    val allBooks: LiveData<List<Book>>

    init {
        val wordsDao = BooksRoomDatabase.getDatabase(application).bookDao()
        repository = BooksRepository(wordsDao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = scope.launch(Dispatchers.IO) {
        repository.insert(book)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
package ru.rebrain.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.rebrain.room.dao.BookDao
import ru.rebrain.room.model.Book

class WordRepository(
    private val bookDao: BookDao
) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
}
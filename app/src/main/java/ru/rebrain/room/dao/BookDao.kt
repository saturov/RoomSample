package ru.rebrain.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.rebrain.room.model.Book

@Dao
interface BookDao {

    @Query("SELECT * from books_table ORDER BY title ASC")
    fun getAllBooks(): List<Book>

    @Insert
    fun insert(book: Book)

    @Query("DELETE FROM books_table")
    fun deleteAll()
}
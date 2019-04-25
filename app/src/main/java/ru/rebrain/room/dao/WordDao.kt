package ru.rebrain.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.rebrain.room.model.Book

@Dao
interface WordDao {

    @Query("SELECT * from books_table ORDER BY title ASC")
    fun getAllWords(): List<Book>

    @Insert
    fun insert(word: Book)

    @Query("DELETE FROM books_table")
    fun deleteAll()
}
package ru.rebrain.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.rebrain.room.dao.BookDao
import ru.rebrain.room.model.Book

@Database(entities = [Book::class], version = 1)
public abstract class BooksRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: BooksRoomDatabase? = null

        fun getDatabase(context: Context): BooksRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksRoomDatabase::class.java,
                    "Books_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun bookDao(): BookDao
}
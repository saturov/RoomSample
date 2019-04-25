package ru.rebrain.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey @ColumnInfo(name = "title") val title: String
)
package com.nextory.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    var favorite: Boolean = false
)

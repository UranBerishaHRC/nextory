package com.nextory.testapp.data

import androidx.annotation.VisibleForTesting
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


object Migrations1To2 {
    /**
     * Migrate from:
     * version 1 - using Room
     * to
     * version 2 - using Room where the [Book] has an extra field: favorite
     */
    @VisibleForTesting
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE book"
                        + " ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
}

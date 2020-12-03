package com.phiruse.roomapplication.data.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

        /**
         * Insert an object in the database.
         *
         * @param obj the object to be inserted.
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(obj: T): Long

        /**
         * Insert an array of objects in the database.
         *
         * @param obj the objects to be inserted.
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(obj: List<T>)

        /**
         * Update an object from the database.
         *
         * @param obj the object to be updated
         */
        @Update
        fun update(obj: T)

        /**
         * Delete an object from the database
         *
         * @param obj the object to be deleted
         */
        @Delete
        fun delete(obj: T)

}

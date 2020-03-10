package io.bsu.mmf.helpme.data.database.db.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(type: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<T>): List<Long>

    @Update
    suspend fun update(type: T)

    @Update
    suspend fun update(obj: List<T>)

    @Delete
    suspend fun delete(type: T)

    @Delete
    suspend fun delete(obj: List<T>)

}
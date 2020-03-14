package io.bsu.mmf.helpme.common.dataSource.base


interface BaseLocalDataSource<T> {
    fun insert(type: T): Long

    suspend fun update(type: T)

    suspend fun insert(obj: List<T>): List<Long>
    suspend fun update(obj: List<T>)
    suspend fun delete(type: T)
    suspend fun delete(obj: List<T>)
}
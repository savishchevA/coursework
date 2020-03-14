package io.bsu.mmf.helpme.common.dataSource.base

import io.bsu.mmf.helpme.common.database.db.dao.BaseDao
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.common.mappers.toListMapper

abstract class BaseLocalDataSourceImpl<F, T>(
    private val baseDao: BaseDao<T>,
    private val mapperToRooItem: Mapper<F, T>
): BaseLocalDataSource<F> {

    override fun insert(type: F): Long {
        return baseDao.insert(mapperToRooItem.map(type))
    }

    override suspend fun update(type: F) {
        baseDao.update(mapperToRooItem.map(type))
    }

    override suspend fun insert(obj: List<F>): List<Long> {
        return baseDao.insert(mapperToRooItem.toListMapper().map(obj))
    }

    override suspend fun update(obj: List<F>) {
        baseDao.update(mapperToRooItem.toListMapper().map(obj))
    }

    override suspend fun delete(type: F) {
        baseDao.delete(mapperToRooItem.map(type))
    }

    override suspend fun delete(obj: List<F>) {
        baseDao.delete(mapperToRooItem.toListMapper().map(obj))
    }

}
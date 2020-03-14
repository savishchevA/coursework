package io.bsu.mmf.helpme.common.mappers

interface Mapper<F, T> {
    fun map(from: F): T
}

interface MapperWithParam<F1, F2, T> {
    fun map(from: F1, fromF2: F2): T
}

interface IndexedMapper<F, T> {
    fun map(index: Int, from: F): T
}

private class MapperToListMapper<F, T>(val singleMapper: Mapper<F, T>) : Mapper<List<F>, List<T>> {
    override fun map(from: List<F>): List<T> = from.map(singleMapper::map)
}

private class IndexedMapperToListMapper<F, T>(val singleMapper: IndexedMapper<F, T>) :
    Mapper<List<F>, List<T>> {
    override fun map(from: List<F>): List<T> = from.mapIndexed(singleMapper::map)
}

fun <F, T> Mapper<F, T>.toListMapper(): Mapper<List<F>, List<T>> = MapperToListMapper(this)
fun <F, T> IndexedMapper<F, T>.toListMapper(): Mapper<List<F>, List<T>> = IndexedMapperToListMapper(this)


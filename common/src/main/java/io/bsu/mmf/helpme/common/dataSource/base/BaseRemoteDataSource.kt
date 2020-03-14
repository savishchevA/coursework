package io.bsu.mmf.helpme.common.dataSource.base

import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.ResultNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.gildor.coroutines.retrofit.awaitResponse
import retrofit2.Call
import retrofit2.awaitResponse
import timber.log.Timber





class BaseRemoteDataSource {

    suspend fun executeEmptyResponse(
            call: Call<Unit>
    ): io.bsu.mmf.helpme.data.ResultNetwork<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.awaitResponse()
                if (response.isSuccessful) {

                    io.bsu.mmf.helpme.data.ResultNetwork.Success(Unit)
                } else {
                    io.bsu.mmf.helpme.data.ResultNetwork.Error.NetworkError(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                io.bsu.mmf.helpme.data.ResultNetwork.Error.NetworkError((e))
            }

        }
    }


    suspend inline fun <reified F, T> executeResponseWithMapper(
            call: Call<F>,
            serviceItemMapper: Mapper<F, T>
    ): io.bsu.mmf.helpme.data.ResultNetwork<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response = call.awaitResponse()
                if (response.isSuccessful) {
                    val resultData = serviceItemMapper.map((response.body()!!))
                    io.bsu.mmf.helpme.data.ResultNetwork.Success(resultData)
                } else {
                    io.bsu.mmf.helpme.data.ResultNetwork.Error.NetworkError(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                Timber.e(e)
                io.bsu.mmf.helpme.data.ResultNetwork.Error.NetworkError((e))
            }

        }


    }
}
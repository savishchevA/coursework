package io.bsu.mmf.helpme.data.dataSource.base

import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.ResultNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import ru.gildor.coroutines.retrofit.awaitResponse
import retrofit2.Call
import retrofit2.awaitResponse
import timber.log.Timber





class BaseRemoteDataSource {

    suspend fun executeEmptyResponse(
            call: Call<Unit>
    ): ResultNetwork<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.awaitResponse()
                if (response.isSuccessful) {

                    ResultNetwork.Success(Unit)
                } else {
                    ResultNetwork.Error.NetworkError(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                ResultNetwork.Error.NetworkError((e))
            }

        }
    }


    suspend inline fun <reified F, T> executeResponseWithMapper(
            call: Call<F>,
            serviceItemMapper: Mapper<F, T>
    ): ResultNetwork<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response = call.awaitResponse()
                if (response.isSuccessful) {
                    val resultData = serviceItemMapper.map((response.body()!!))
                    ResultNetwork.Success(resultData)
                } else {
                    ResultNetwork.Error.NetworkError(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                Timber.e(e)
                ResultNetwork.Error.NetworkError((e))
            }

        }


    }
}
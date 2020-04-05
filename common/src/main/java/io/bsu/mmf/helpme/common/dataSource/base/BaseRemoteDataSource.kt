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
    ): ResultNetwork<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.awaitResponse()
                if (response.isSuccessful) {
                    ResultNetwork.Success(Unit)
                } else {
                    ResultNetwork.Error(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                ResultNetwork.Error((e.message ?: ""))
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
                    ResultNetwork.Error(String(response.errorBody()!!.bytes()))
                }
            } catch (e: Throwable) {
                Timber.e(e)
                ResultNetwork.Error((e.message ?: ""))
            }

        }


    }
}
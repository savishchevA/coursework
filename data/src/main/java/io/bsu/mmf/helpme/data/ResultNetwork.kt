package io.bsu.mmf.helpme.data

sealed class ResultNetwork<out R> {
    data class Success<T>(val data: T) : ResultNetwork<T>()
    object Loading : ResultNetwork<Nothing>()
    data class Error(val errorMessage: String) : ResultNetwork<Nothing>()

    //Used to get result status
    val isFailure: Boolean
        get() = this is Error
    val isSuccess: Boolean
        get() = this is Success
    val onLoading: Boolean
        get() = this is Loading

    inline fun fold(
        onSuccess: (data: R) -> Unit = {},
        onFailure: (error: Error) -> Unit = {},
        onLoading: (isLoading: Boolean) -> Unit = {}
    ) {

        when (this) {
            is Success<R> -> {
                onSuccess(data)
            }
            is Error -> {
                onFailure(this)
            }
            is Loading -> {
                onLoading(true)
            }
        }
    }
}
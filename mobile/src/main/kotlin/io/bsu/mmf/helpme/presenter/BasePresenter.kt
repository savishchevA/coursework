package io.bsu.mmf.helpme.presenter

import android.content.Context
import androidx.annotation.StringRes

import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*



//abstract class  BasePresenter<T : BaseView?> : MvpPresenter<T>() {
//
//    @Inject
//    lateinit var context: Context
//
//    private val job = Job()
//    private var isMissNetworkDialogShow = false
//    val localRepositoryScope = CoroutineScope(Dispatchers.Main + job)
//
//    protected val compositeDisposable = CompositeDisposable()
//
//    //TODO: remove
//    fun createJob() {
//        /*  job = Job()
//          localRepositoryScope = CoroutineScope(Dispatchers.Main + job)*/
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        job.cancel()
//        compositeDisposable.clear()
//    }
//
//    //TODO: remove
//    fun cancelJob() {
//        /* job.cancel()
//         localRepositoryScope.cancel()*/
//    }
//
//    protected open fun <T> load(
//        call: suspend () -> T,
//        onComplete: (result: Result<T>) -> Unit
//    ) = localRepositoryScope.launch {
//        val result = runCatching { call() }
//        onComplete(result)
//    }
//
//    protected fun getString(@StringRes stringRes: Int): String = context.getString(stringRes)
//    protected fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String =
//        context.getString(stringRes, *formatArgs)
//
//
//
//
//
//}


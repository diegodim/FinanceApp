package duarte.diego.financeapp.uikit.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import duarte.diego.financeapp.uikit.utils.extensions.observeLiveData


interface ViewStateListener {

    fun onStateError(error: Throwable)

    fun onStateLoading()

    fun hideLoading()

    private fun <T> ViewState<T>.handle(
        onError: ((Throwable) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {

        stateHandler(
            onSuccess = {
                onSuccess?.invoke(it)
                onComplete?.invoke()
            },
            onError = {
                onError?.invoke(it) ?: onStateError(it)
                onComplete?.invoke()
            },
            loading = {
                onLoading?.invoke() ?: onStateLoading()
            }
        )
    }

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        observeLiveData(lifecycleOwner) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }

    }

    fun <T> LiveData<ViewState<T>>.onFirstPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: (T) -> Unit
    ) {
        observeLiveData(lifecycleOwner, true) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }
    }

}
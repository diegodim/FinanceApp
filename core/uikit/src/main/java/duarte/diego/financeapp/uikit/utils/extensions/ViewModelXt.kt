package duarte.diego.financeapp.uikit.utils.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duarte.diego.financeapp.domain.core.UseCase
import duarte.diego.financeapp.uikit.core.ViewState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent
        = inject<U> {
    parametersOf(viewModelScope)
}
package com.mreigosa.marvelapp.domain.executor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInvoker(
    private val dispatcherProvider: DispatcherProvider = DispatcherProviderImpl(),
    private val idleNotifier: IdleNotifier? = null
) : Invoker {

    override fun <P : Any, T> execute(
        scope: CoroutineScope,
        useCase: UseCase<P, T>,
        callback: ((Result<T>) -> Unit)?
    ) {
        idleNotifier?.increment()
        scope.launch(dispatcherProvider.main) {
            try {
                val result = withContext(dispatcherProvider.background) { useCase.run() }
                callback?.invoke(result)
            } catch (e: Exception) {
                callback?.invoke(Result.failure(e))
            } finally {
                idleNotifier?.decrement()
            }
        }
    }
}
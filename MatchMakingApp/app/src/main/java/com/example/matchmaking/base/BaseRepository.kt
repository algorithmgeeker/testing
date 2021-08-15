package com.example.matchmaking.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseRepository @Inject constructor() {
    private val compositeDisposable = CompositeDisposable()

    fun dispose() {
        if (compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

    protected open fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
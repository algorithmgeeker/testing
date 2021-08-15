package com.example.matchmaking.base

import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    protected lateinit var liveData: SingleLiveEvent<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initViewBinding()
        onCreated()
        initViewState()
    }

    protected abstract fun initViewModel()
    protected abstract fun initViewBinding()
    protected abstract fun onCreated()
    protected abstract fun initViewState()

    protected open fun getViewModel(type: Class<out ViewModel>): ViewModel? {
        val vm = ViewModelProvider(this, viewModelFactory).get(type)
        return vm
    }

    protected fun showToast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
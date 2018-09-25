package com.toapplication.wordpressapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.Animation
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.toapplication.wordpressapp.util.AppUtility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseActivity: AppCompatActivity(){
    private val TAG = BaseActivity::class.java.simpleName
    protected var compositeDisposable: CompositeDisposable? = null
    private var connectionLost: Boolean = false
    private var networkDisposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()

    }

    override fun onStart() {
        super.onStart()
        networkDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isConnectedToInternet ->
                    if (!isConnectedToInternet) {
                        connectionLost = true
                        AppUtility.sharedInstance().isNetworkConnected = false
                        onConnectionChange(false)
                        Log.e(TAG, "Device has not connection ")
                    } else if (connectionLost) {
                        AppUtility.sharedInstance().isNetworkConnected = true
                        onConnectionChange(true)
                        connectionLost = false
                    }

                }) { throwable -> Log.e(TAG, "ReactiveNetwork: ", throwable) }

        compositeDisposable!!.add(networkDisposable!!)
    }

    fun onConnectionChange(isConnected: Boolean) {

    }

}
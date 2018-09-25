package com.toapplication.wordpressapp.webservice

import android.util.Log
import com.toapplication.wordpressapp.helper.WebServiceHelper
import com.toapplication.wordpressapp.manager.RealmManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceManager {

    private var mWebServiceClient = WebServiceHelper().getRetrofitClient()

    interface ISuccessCallback {
        fun onSuccess(status: String, data: Any?)
    }

    fun getPosts(categoryId: Int, page: Int, successCallback: ISuccessCallback) {
        mWebServiceClient.getCategoryPosts(categoryId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    //                    successCallback.onSuccess(response.status, response.pages, response.posts)
//                    RealmManager.insertAllPosts(response.posts)
                }, { err -> Log.e(TAG, "getRecentPosts: error ") })
    }
    fun getRecentPosts(page: Int, successCallback: ISuccessCallback) {
        mWebServiceClient.getRecentPosts(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    successCallback.onSuccess(response.status, response)
//                    RealmManager.insertAllPosts(response.posts)
                }, { err ->
                    successCallback.onSuccess("no", null)
                    Log.e(TAG, "getRecentPosts: error ") })
    }

    fun getCategories(successCallback: ISuccessCallback){
        mWebServiceClient.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    successCallback.onSuccess(response.status, response)
                }, { err ->
                    successCallback.onSuccess("no", null)
                    Log.e(TAG, "getRecentPosts: error ") })
    }

    companion object {
        const val TAG = "ServiceManager"
        private var instance: ServiceManager? = null

        fun getInstance(): ServiceManager {
            if (instance == null)
                instance = ServiceManager()

            return instance as ServiceManager
        }
    }
}
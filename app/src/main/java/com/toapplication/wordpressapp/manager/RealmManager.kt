package com.toapplication.wordpressapp.manager

import android.annotation.SuppressLint
import com.toapplication.wordpressapp.data.categorymodel.Category
import com.toapplication.wordpressapp.data.postmodel.Full
import com.toapplication.wordpressapp.data.postmodel.Post
import com.toapplication.wordpressapp.data.postmodel.Thumbnail
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort

open class RealmManager {

    private val DBFileName = "wp2app.realm"
    private val realmConfiguration: RealmConfiguration? = null

    fun getAllPosts(): List<Post> {
        return getInstance()
                .where(Post::class.java)
                .sort("date", Sort.DESCENDING)
                .findAll()
    }

    fun getCategories(): List<Category>{
        return getInstance()
                .where(Category::class.java)
                .findAll()
    }

    fun insertPosts(postList: List<Post>) {
        getInstance().executeTransaction({ realm ->
            val list: ArrayList<Post> = ArrayList()
            for (post in postList) {
                if (post.thumbnail_images != null) {
                    post.thumbnail_images = Thumbnail(post.id, post.thumbnail_images?.full)
                    if (post.thumbnail_images?.full != null)
                        post.thumbnail_images?.full = Full(post.id, post.thumbnail_images?.full?.url, post.thumbnail_images?.full?.width, post.thumbnail_images?.full?.height)

                }
                list.add(post)
            }
            realm.insertOrUpdate(list)
        })
    }

    fun insertCategories(categories: List<Category>){
        getInstance().executeTransaction({realm ->  realm.insertOrUpdate(categories)})
    }

    companion object {
        const val TAG = "RealmManager"
        @SuppressLint("StaticFieldLeak")
        private var instance: Realm? = null
        private var mManageInstance: RealmManager? = null


        fun getInstance(): Realm {
            if (instance == null) {
                instance = Realm.getDefaultInstance()
            }
            return instance as Realm
        }

        fun getManagerInstance(): RealmManager {
            if (mManageInstance == null) {
                mManageInstance = RealmManager()
            }
            return mManageInstance as RealmManager
        }
    }
}
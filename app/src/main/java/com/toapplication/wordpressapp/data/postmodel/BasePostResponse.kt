package com.toapplication.wordpressapp.data.postmodel

import io.realm.RealmList
import java.io.Serializable

data class BasePostResponse(var count: String,
                            var status: String,
                            var pages: Int = 0,
                            var posts: RealmList<Post>,
                            var count_total: String): Serializable
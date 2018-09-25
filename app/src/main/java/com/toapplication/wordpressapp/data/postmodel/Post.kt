package com.toapplication.wordpressapp.data.postmodel

import com.toapplication.wordpressapp.data.categorymodel.Category
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Post(@PrimaryKey
                var id: String = "",
                var status: String? = null,
                var type: String? = null,
                var date: String? = null,
                var url: String? = null,
                var content: String? = null,
                var title: String? = null,
                var thumbnail_images: Thumbnail? = null,
                var categories: RealmList<Category>? = null) : RealmObject(), Serializable {

}
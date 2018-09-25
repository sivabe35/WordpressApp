package com.toapplication.wordpressapp.data.categorymodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Category(@PrimaryKey
                    var id: Int = 0,
                    var slug: String? = null,
                    var title: String? = null,
                    var description: String? = null,
                    var parent: Int? = 0,
                    var post_count: Int? = 0) : RealmObject(), Serializable {
}

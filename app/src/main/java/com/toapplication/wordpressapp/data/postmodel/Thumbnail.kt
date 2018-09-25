package com.toapplication.wordpressapp.data.postmodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Thumbnail(@PrimaryKey
                     var id: String = "",
                     var full: Full? = null) : RealmObject(), Serializable {
}
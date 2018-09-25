package com.toapplication.wordpressapp.data.postmodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Full(@PrimaryKey
                var id: String = "",
                var url: String? = null,
                var width: Int? = 0,
                var height: Int? = 0) : RealmObject(), Serializable {
}


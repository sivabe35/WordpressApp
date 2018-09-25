package com.toapplication.wordpressapp.data.categorymodel

import java.io.Serializable

data class BaseCategoryResponse(var status: String,
                var count: Int = 0,
                var categories: List<Category>): Serializable
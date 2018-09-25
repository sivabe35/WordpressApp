package com.toapplication.wordpressapp.util

import android.graphics.Color
import com.toapplication.wordpressapp.manager.UserManager

class AppUtility {
    var isNetworkConnected: Boolean = false

    fun getColorFromHex(hex: String?): Int {
        if (hex == null)
            return Color.parseColor("#ffffff")

        val color = if(!hex.startsWith("#")) "#${hex.trim()}" else hex.trim()
        return Color.parseColor(color)
    }


    companion object {
        const val TAG = "AppUtility"
        private var instance: AppUtility? = null

        fun sharedInstance(): AppUtility {
            if (instance == null) {
                instance = AppUtility()

            }
            return instance as AppUtility
        }
    }
}
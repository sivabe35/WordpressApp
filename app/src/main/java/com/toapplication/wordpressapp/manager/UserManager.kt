package com.toapplication.wordpressapp.manager


class UserManager {

    var postFeedType = PostFeedType.Column


    enum class PostFeedType private constructor(private val value: String) {
        Column("Single"),
        List("List");

        fun value(): String {
            return value
        }
    }

    companion object {
        const val TAG = "UserManager"
        private var activeUser: UserManager? = null

        fun getActiveUser(): UserManager {
            if (activeUser == null) {
                activeUser = UserManager()
//                val defaultLocale = Locale.getDefault()
//                if (defaultLocale != null) {
//                    if (activeUser!!.languageCode == null)
//                        activeUser!!.languageCode = Locale.getDefault().language
//                }
            }
            return activeUser as UserManager
        }
    }
}
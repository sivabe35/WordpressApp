package com.toapplication.wordpressapp.data.event

import com.toapplication.wordpressapp.manager.UserManager

data class ViewTypeChangedEvent(var viewType: UserManager.PostFeedType, var isChanged: Boolean)
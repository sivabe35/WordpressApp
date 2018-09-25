package com.toapplication.wordpressapp.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toapplication.wordpressapp.R

class SavedPostFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_posts, container, false)
    }

    companion object {
        const val TAG = "SavedPostFragment"

        fun newInstance(): SavedPostFragment {
            val fragment = SavedPostFragment()
            return fragment
        }

    }
}
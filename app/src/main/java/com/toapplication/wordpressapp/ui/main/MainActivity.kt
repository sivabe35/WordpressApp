package com.toapplication.wordpressapp.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.toapplication.wordpressapp.BaseActivity
import com.toapplication.wordpressapp.R
import com.toapplication.wordpressapp.constant.BottomNavIndex
import com.toapplication.wordpressapp.data.event.ViewTypeChangedEvent
import com.toapplication.wordpressapp.manager.UserManager
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        switchFragments(p0.order)
        return true
    }

    private fun switchFragments(order: Int) {
        val fragmentToShow: Fragment
        val fragmentTagToShow: String
        when (order) {
            BottomNavIndex.SAVED.ordinal -> {
                fragmentToShow = SavedPostFragment.newInstance()
                fragmentTagToShow = SavedPostFragment.TAG
            }
            BottomNavIndex.SEARCH.ordinal -> {
                fragmentToShow = SearchFragment.newInstance()
                fragmentTagToShow = SearchFragment.TAG
            }
            else -> {
                fragmentToShow = MainFragment.newInstance()
                fragmentTagToShow = MainFragment.TAG
            }
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentToShow, fragmentTagToShow)
                .addToBackStack(fragmentTagToShow)
                .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        switchFragments(BottomNavIndex.MAIN.ordinal)
        setupBottomNavBar()
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_hamburger)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            if(UserManager.getActiveUser().postFeedType == UserManager.PostFeedType.Column)
                UserManager.getActiveUser().postFeedType = UserManager.PostFeedType.List
            else
                UserManager.getActiveUser().postFeedType = UserManager.PostFeedType.Column
            EventBus.getDefault().post(ViewTypeChangedEvent(UserManager.getActiveUser().postFeedType, true))
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupBottomNavBar() {
        bottomNavigation.onNavigationItemSelectedListener = this
//        bottomNavigation.currentItem = BottomNavIndex.MAIN.ordinal
//        bottomNavigation.currentItem = BottomNavIndex.MAIN.ordinal
//        bottomNavigation.enableAnimation(false)
//        bottomNavigation.enableShiftingMode(false)
//        bottomNavigation.enableItemShiftingMode(false)
//        bottomNavigation.setTextVisibility(false)
        //        KeyboardVisibilityEvent.setEventListener(this@MainActivity, { isOpen ->
        //            if (isOpen) {
        //                bottomNavigation.setVisibility(View.GONE)
        //            } else {
        //                Handler().postDelayed({ bottomNavigation.visibility = View.VISIBLE }, 50)
        //            }
        //        })
    }
}

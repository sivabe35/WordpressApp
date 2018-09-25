package com.toapplication.wordpressapp.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toapplication.wordpressapp.R
import com.toapplication.wordpressapp.data.event.ViewTypeChangedEvent
import com.toapplication.wordpressapp.data.postmodel.BasePostResponse
import com.toapplication.wordpressapp.data.postmodel.Post
import com.toapplication.wordpressapp.manager.RealmManager
import com.toapplication.wordpressapp.manager.UserManager
import com.toapplication.wordpressapp.webservice.ServiceManager
import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode



class MainFragment: Fragment() {

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mPostList: MutableList<Post>? = null
    private var mAdapter: MainAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getData()
    }

    private fun initRecyclerView() {
        mLayoutManager = if (UserManager.getActiveUser().postFeedType == UserManager.PostFeedType.Column)
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        else
            LinearLayoutManager(context)
        mAdapter = MainAdapter(mPostList, context!!)
        mainRecyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onViewTypeChanged(event: ViewTypeChangedEvent) {
        if(event.isChanged){
            initRecyclerView()
            mAdapter!!.notifyDataSetChanged()
        }
    }

    fun getData(){
        ServiceManager.getInstance().getRecentPosts(0, object : ServiceManager.ISuccessCallback {
            override fun onSuccess(status: String, data: Any?) {
                if(status == "ok"){
                    mPostList = (data as BasePostResponse).posts
                    RealmManager.getManagerInstance().insertPosts(mPostList!!)
                    mAdapter!!.updateAdapter(mPostList!!)
                }else{
                    mPostList = RealmManager.getManagerInstance().getAllPosts() as MutableList<Post>
                    mAdapter!!.updateAdapter(RealmManager.getManagerInstance().getAllPosts())
                }
            }
        })
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }

    }
}
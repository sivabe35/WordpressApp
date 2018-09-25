package com.toapplication.wordpressapp.ui.leftmenu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toapplication.wordpressapp.R
import com.toapplication.wordpressapp.data.categorymodel.BaseCategoryResponse
import com.toapplication.wordpressapp.data.categorymodel.Category
import com.toapplication.wordpressapp.manager.RealmManager
import com.toapplication.wordpressapp.webservice.ServiceManager
import kotlinx.android.synthetic.main.fragment_left_menu.*

class LeftMenuFragment: Fragment() {

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mCategorList: List<Category>? = null
    private var mAdapter: LeftMenuAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_left_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getData()
    }

    private fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(context)
        mAdapter = LeftMenuAdapter(context!!, mCategorList)
        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    private fun getData(){
        ServiceManager.getInstance().getCategories( object : ServiceManager.ISuccessCallback {
            override fun onSuccess(status: String, data: Any?) {
                if(status == "ok"){
                    mCategorList = (data as BaseCategoryResponse).categories
                    RealmManager.getManagerInstance().insertCategories(mCategorList!!)
                    mAdapter!!.updateAdapter(mCategorList!!)
                }else{
                    mCategorList = RealmManager.getManagerInstance().getCategories()
                    if(mCategorList != null)
                        mAdapter!!.updateAdapter(mCategorList!!)
                }
            }
        })
    }

    companion object {
        const val TAG = "LeftMenuFragment"

        fun newInstance(): LeftMenuFragment {
            val fragment = LeftMenuFragment()

//            val bundle = Bundle()
//            bundle.putSerializable("DiscoveryObjects", discoveryObjects)
//            bundle.putInt("SelectedTabPosition", selectedTabPosition)
//            fragment.arguments = bundle

            return fragment
        }

    }
}
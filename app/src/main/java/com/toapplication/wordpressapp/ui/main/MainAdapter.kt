package com.toapplication.wordpressapp.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.toapplication.wordpressapp.IPostClickListener
import com.toapplication.wordpressapp.R
import com.toapplication.wordpressapp.data.postmodel.Post
import com.toapplication.wordpressapp.manager.UserManager

class MainAdapter(postsToShow: MutableList<Post>? = ArrayList(), private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPostsToShow: MutableList<Post>? = postsToShow
    private var mPostClickListener: IPostClickListener? = null
//    private var mLongClickListener: ILongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (UserManager.getActiveUser().postFeedType === UserManager.PostFeedType.Column)
            v = LayoutInflater.from(context).inflate(R.layout.post_column_item, parent, false)
        else
            v = LayoutInflater.from(context).inflate(R.layout.post_list_item, parent, false)

        val vh = MainViewHolder(context, v)
        vh.postClickListener = mPostClickListener
//        vh.longClickListener = mLongClickListener
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as MainViewHolder
        vh.bindViews(position, mPostsToShow)

    }

    override fun getItemCount(): Int {
        return if(mPostsToShow == null) 0 else mPostsToShow!!.size
    }

    fun setOnClickListener(clickListener: IPostClickListener) {
        mPostClickListener = clickListener
    }

//    fun setLongClickListener(clickListener: ILongClickListener) {
//        mLongClickListener = clickListener
//    }


    fun updateAdapter(newList: List<Post>) {
        mPostsToShow = ArrayList()
//        mPostsToShow!!.clear()
        mPostsToShow!!.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearAllNews() {
        mPostsToShow!!.clear()
        notifyDataSetChanged()
    }

    fun addNewsToEnd(list: List<Post>?) {
        if (list == null || list.isEmpty())
            return

        mPostsToShow!!.addAll(list)
        notifyItemRangeInserted(itemCount - list.size + 1, list.size)
    }

    fun addNewsToBegin(list: List<Post>?) {
        if (list == null || list.isEmpty())
            return

        for (i in list.indices.reversed())
            mPostsToShow!!.add(0, list[i])

        notifyDataSetChanged()
    }

    inner class MainViewHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postClickListener: IPostClickListener? = null
        //        var longClickListener: ILongClickListener? = null
        var mTitleText = itemView.findViewById<TextView>(R.id.textView)
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindViews(position: Int, data: List<Post>?) {
            if (data == null)
                return

            mTitleText.text = data[position].title
            val options = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            val thumbnail = data[position].thumbnail_images?.full?.url
            Glide.with(itemView).load(thumbnail).apply(options).into(imageView)

        }
    }
}
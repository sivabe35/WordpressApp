package com.toapplication.wordpressapp.ui.leftmenu

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.text.TextUtilsCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.facebook.stetho.common.StringUtil
import com.toapplication.wordpressapp.IPostClickListener
import com.toapplication.wordpressapp.R
import com.toapplication.wordpressapp.data.categorymodel.Category
import com.toapplication.wordpressapp.data.postmodel.Post
import com.toapplication.wordpressapp.util.AppUtility
import org.apache.commons.lang3.StringUtils


class LeftMenuAdapter(private val context: Context, private var mCategories: List<Category>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.left_menu_item, parent, false)
        return LeftMenuViewHolder(context, v)
    }

    override fun getItemCount(): Int {
//        return 10
        return if(mCategories == null) 0 else mCategories!!.size
    }

    fun updateAdapter(newList: List<Category>) {
        mCategories = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as LeftMenuAdapter.LeftMenuViewHolder
        vh.bindViews(position, mCategories)
    }

    inner class LeftMenuViewHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postClickListener: IPostClickListener? = null
        //        var longClickListener: ILongClickListener? = null
        var categoryTextView = itemView.findViewById<TextView>(R.id.categoryTextView)
        var categoryIcon = itemView.findViewById<ImageView>(R.id.categoryIcon)
        var leftBgView = itemView.findViewById<View>(R.id.leftBgView)
        var rootLayout = itemView.findViewById<ConstraintLayout>(R.id.rootLayout)

        fun bindViews(position: Int, data: List<Category>?) {
            if (data == null)
                return
            val iconUrl = StringUtils.substringBetween(data[position].description, "//icon//","//endicon//")
            val categoryColor = StringUtils.substringBetween(data[position].description, "//color//","//endcolor//")
            leftBgView.setBackgroundColor(AppUtility.sharedInstance().getColorFromHex(categoryColor))
            Glide.with(context).load(iconUrl).into(categoryIcon)
            categoryTextView.text = data[position].title

//
//            mTitleText.text = data[position].title
//            val options = RequestOptions()
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//            val thumbnail = data[position].thumbnail_images?.full?.url
//            Glide.with(itemView).load(thumbnail).apply(options).into(imageView)

        }
    }

}
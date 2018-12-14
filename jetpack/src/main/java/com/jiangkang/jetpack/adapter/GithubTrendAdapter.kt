package com.jiangkang.jetpack.adapter

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jiangkang.hybrid.Khybrid
import com.jiangkang.jetpack.R
import com.jiangkang.jetpack.data.Item
import org.jetbrains.anko.sdk27.coroutines.onClick

class GithubTrendAdapter(context: Context) : ListAdapter<Item, GithubTrendAdapter.ViewHolder>(GithubTrendDiffCallback()) {

    private val mContext: Context = context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivIcon = itemView.findViewById<ImageView>(R.id.iv_icon)
        val tvProjectName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvAuthor = itemView.findViewById<TextView>(R.id.tv_author)
        val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        val tvStars = itemView.findViewById<TextView>(R.id.tv_star)
        val tvForks = itemView.findViewById<TextView>(R.id.tv_fork)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_trend, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.apply {

            Glide.with(mContext)
                    .load(data.owner.avatar_url)
                    .into(ivIcon)

            tvProjectName.text = data.full_name
            tvAuthor.text = data.language
            tvDesc.text = data.description
            tvStars.text = data.stargazers_count.toString()
            tvForks.text = data.forks_count.toString()

            itemView.onClick {
                Khybrid().loadUrl(mContext, data.html_url)
            }
        }


    }


}

class GithubTrendDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(p0: Item, p1: Item): Boolean {
        return p0.id == p1.id
    }

    override fun areContentsTheSame(p0: Item, p1: Item): Boolean {
        return p0 == p1
    }

//    override fun areItemsTheSame(oldItem: Item?, newItem: Item?): Boolean {
//        return oldItem?.id == newItem?.id
//    }
//
//    override fun areContentsTheSame(oldItem: Item?, newItem: Item?): Boolean {
//        return oldItem == newItem
//    }

}

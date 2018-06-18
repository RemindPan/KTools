package com.jiangkang.jetpack.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jiangkang.jetpack.R
import com.jiangkang.jetpack.data.Item
import kotlinx.android.synthetic.main.item_github_trend.view.*

class GithubTrendAdapter : ListAdapter<Item, GithubTrendAdapter.ViewHolder>(GithubTrendDiffCallback()) {

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

            Glide.with(holder.itemView.context)
                    .load(data.owner.avatar_url)
                    .into(ivIcon)

            tvProjectName.text = data.full_name
            tvAuthor.text = data.language
            tvDesc.text = data.description
            tvStars.text = data.stargazers_count.toString()
            tvForks.text = data.forks_count.toString()

        }
    }


}

class GithubTrendDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem == newItem
    }

}

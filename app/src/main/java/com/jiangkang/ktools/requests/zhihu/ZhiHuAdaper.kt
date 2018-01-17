package com.jiangkang.ktools.requests.zhihu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jiangkang.ktools.R
import com.jiangkang.requests.zhihu.bean.Story
import com.jiangkang.tools.utils.ToastUtils
import kotlinx.android.synthetic.main.item_article.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by jiangkang on 2018/1/17.
 * description：知乎API相关
 */

class ZhiHuAdaper(val data: ArrayList<Story>) : RecyclerView.Adapter<ZhiHuAdaper.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_article, null)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(story: Story) {
            Glide.with(itemView.context)
                    .load(story.imageUrlList[0])
                    .into(itemView.ivArticle)

            itemView.tvArticle.text = story.title

            itemView.onClick {
                ToastUtils.showShortToast("点击了item")
            }
        }


    }

}
package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter (private val listener : NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private val item : ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // layoutinflater ek aisi class hai jo xml ko view me convert kar deti hai
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
           listener.onItemClicked(item[viewHolder.adapterPosition])                                                             // adapter is for only data passing .. so it will not be used for what will will be done after it is clicked .. we have to implement something that mainactivity handle the click
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)

        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView : TextView = itemView.findViewById(R.id.title)
    val image : ImageView = itemView.findViewById(R.id.image)
    val author : TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item : News)
}
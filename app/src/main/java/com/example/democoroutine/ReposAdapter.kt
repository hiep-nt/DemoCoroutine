package com.example.democoroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.democoroutine.model.Repo
import kotlinx.android.synthetic.main.repo_view_item.view.*

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.DataViewHolder>() {

    var data: List<Repo> = listOf()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: List<Repo>, position: Int) {
            val item = data[position]
            itemView.apply {
                repo_name.text = item.fullName
                repo_description.text = item.description
                repo_language.text = item.language
                repo_stars.text = item.stars.toString()
                repo_forks.text = item.forks.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repo_view_item, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = data.size
}
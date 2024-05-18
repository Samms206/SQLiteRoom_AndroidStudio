package com.example.modul4.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4.R
import com.example.modul4.room.PostDatabase
import com.google.android.material.imageview.ShapeableImageView

class PostAdapterRoom(private var postList: List<PostDatabase>) :
    RecyclerView.Adapter<PostAdapterRoom.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelTitle: TextView = itemView.findViewById(R.id.post_title)
        val hotelDesc: TextView = itemView.findViewById(R.id.post_desc)
        val hotelImg: ShapeableImageView = itemView.findViewById(R.id.post_img)
        val hotelLike: TextView = itemView.findViewById(R.id.post_like)

        //btnlike
        val btnLike: LinearLayout = itemView.findViewById(R.id.btn_like)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = postList[position]

        holder.hotelTitle.text = data.name
        holder.hotelDesc.text = data.description.shorten(500)
        holder.hotelLike.text = data.like.toString()

        val uri = Uri.fromFile(data.image)
        holder.hotelImg.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            data.like += 1
            holder.hotelLike.text = data.like.toString()
        }
    }

    override fun getItemCount(): Int = postList.size

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}

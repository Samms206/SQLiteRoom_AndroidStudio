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
import com.google.android.material.textview.MaterialTextView

class PostAdapterRoom(private var postList: List<PostDatabase>) :
    RecyclerView.Adapter<PostAdapterRoom.PostViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: PostDatabase)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelTitle: TextView = itemView.findViewById(R.id.post_title)
        val hotelDesc: TextView = itemView.findViewById(R.id.post_desc)
        val hotelImg: ShapeableImageView = itemView.findViewById(R.id.post_img)
        val hotelLike: TextView = itemView.findViewById(R.id.post_like)

        //btnlike
        val btnLike: LinearLayout = itemView.findViewById(R.id.btn_like)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = postList[position]

        holder.hotelTitle.text = data.name
        holder.hotelDesc.text = data.description.shorten(500)
        holder.hotelLike.text = data.like.toString()

        // Mengatur image
        val uri = Uri.fromFile(data.image)
        holder.hotelImg.setImageURI(uri)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(postList[holder.absoluteAdapterPosition])
        }

        // button Like
        holder.btnLike.setOnClickListener {
            // Tambah 1 pada likeCount dan update UI
            data.like += 1
            holder.hotelLike.text = data.like.toString()
        }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = postList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}

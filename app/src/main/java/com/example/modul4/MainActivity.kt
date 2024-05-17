package com.example.modul4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4.adapter.PostAdapterRoom
import com.example.modul4.room.PostDatabase
import com.example.modul4.room.PostViewModel
import com.example.modul4.room.PostViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Mendeklarasikan ViewModel untuk interaksi dengan database
    private lateinit var postViewModel: PostViewModel
    // Mendeklarasikan adapter untuk RecyclerView
    private lateinit var postAdapterRoom: PostAdapterRoom
    // Mendeklarasikan RecyclerView untuk menampilkan daftar pemain
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan instance ViewModel
        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java] // ini

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mengamati perubahan data pemain dan memperbarui RecyclerView
        postViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = PostAdapterRoom(postData) //ini
                recyclerView.adapter = postAdapterRoom

                // Menangani aksi klik pada item di RecyclerView
                postAdapterRoom.setOnItemClickCallback(object :
                    PostAdapterRoom.OnItemClickCallback {
                    override fun onItemClicked(data: PostDatabase) { //ini
                        showSelectedPost(data)
                    }
                })
            }
        }
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPost(data: PostDatabase) { // ini
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("player", data)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi yang dipanggil ketika activity di-restart
    override fun onRestart() {
        super.onRestart()

        // Memperbarui daftar pemain
        postViewModel.getAllPost()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }
}
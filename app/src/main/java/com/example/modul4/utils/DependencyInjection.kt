package com.vinz.latihanrecyclerviewpraktikum.utils

import android.content.Context
import com.example.modul4.room.AppDatabase
import com.example.modul4.room.PostRepository
import com.example.recyclerview.room.AppExecutors

object DependencyInjection {
    fun provideRepository(context: Context): PostRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val dao = database.postDao()
        return PostRepository.getInstance(dao, appExecutors)
    }
}
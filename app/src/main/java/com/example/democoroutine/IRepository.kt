package com.example.democoroutine

import androidx.lifecycle.LiveData
import com.example.democoroutine.model.Repo
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getRepos(query: String): Flow<List<Repo>>
}
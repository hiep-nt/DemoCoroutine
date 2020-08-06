package com.example.democoroutine

import android.util.Log
import com.example.democoroutine.api.GithubService
import com.example.democoroutine.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class Repository : IRepository {
    override suspend fun getRepos(query: String): Flow<List<Repo>> {
        val service = GithubService.create()
        return flow {
            emit(service.searchRepos(query, 1, 50).items)
        }.catch{
            emit(emptyList())
        }.flowOn(Dispatchers.IO)

    }
}
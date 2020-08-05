package com.example.democoroutine

import androidx.lifecycle.*
import com.example.democoroutine.api.GithubService
import com.example.democoroutine.api.IN_QUALIFIER
import com.example.democoroutine.model.Repo

class SearchRepoViewModel : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val service = GithubService.create()

    val repoResult : LiveData<List<Repo>> = queryLiveData.switchMap { queryString ->
        liveData {
//            val repoResult = service.searchRepos(queryString,1,50).items
            val data = MutableLiveData(service.searchRepos(queryString,1,50).items)
            emitSource(data)
        }
    }

    fun updateSearch(queryString: String) {
        queryLiveData.value = queryString + IN_QUALIFIER
    }
}
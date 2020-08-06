package com.example.democoroutine

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.democoroutine.api.IN_QUALIFIER
import com.example.democoroutine.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class SearchRepoViewModel(private val repository: IRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    val repoResult: LiveData<List<Repo>> = queryLiveData.switchMap { query ->
        liveData<List<Repo>>(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val flow: Flow<List<Repo>> = repository.getRepos(query)
            val flow2 = (1..50).asFlow()
            emitSource(
                flow.asLiveData()
//                flow.map { it.subList(0, 10) }.asLiveData()
//                flow.transform { list -> emit(list.subList(0, 20)) }.asLiveData()
//                flow.filter { it.first().fullName.equals("keras-team/keras") }.asLiveData()
//                flow.onEach { Log.d("KAI", "OK")}.asLiveData()
            )
        }
    }

    fun updateSearch(queryString: String) {
        queryLiveData.value = queryString + IN_QUALIFIER
    }
}
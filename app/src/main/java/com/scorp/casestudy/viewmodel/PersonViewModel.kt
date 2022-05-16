package com.scorp.casestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorp.casestudy.model.Response
import com.scorp.casestudy.repo.PersonRepository
import com.scorp.casestudy.source.FetchCompletionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PersonRepository) : ViewModel() {

    val response = MutableLiveData<Response>()

    fun loadPersonList(next: String?, needRefresh: Boolean) {
        val handler: FetchCompletionHandler = { fetchResponse, fetchError ->
            response.postValue(Response(fetchResponse, fetchError))
        }
        viewModelScope.launch(Dispatchers.IO) { repository.fetchPersons(next, handler, needRefresh) }
    }

}
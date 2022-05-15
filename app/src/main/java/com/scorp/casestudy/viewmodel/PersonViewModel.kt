package com.scorp.casestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scorp.casestudy.model.Response
import com.scorp.casestudy.repo.PersonRepository
import com.scorp.casestudy.source.FetchCompletionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PersonRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()

    fun getPersonList(next: String?) : MutableLiveData<Response> {
        val handler : FetchCompletionHandler = { fetchResponse, fetchError ->
            response.postValue(Response(fetchResponse, fetchError))
        }
        repository.fetchPersons(next, handler)
        return response
    }

}
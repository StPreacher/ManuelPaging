package com.scorp.casestudy.repo

import com.scorp.casestudy.source.DataSource
import com.scorp.casestudy.source.FetchCompletionHandler
import javax.inject.Inject

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
class PersonRepository @Inject constructor(private val source: DataSource) {

    fun fetchPersons(next: String?, completionHandler: FetchCompletionHandler, needRefresh: Boolean) {
        source.fetch(next, completionHandler, needRefresh)
    }

}
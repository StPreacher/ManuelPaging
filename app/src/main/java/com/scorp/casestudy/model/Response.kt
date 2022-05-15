package com.scorp.casestudy.model

import com.scorp.casestudy.source.FetchError
import com.scorp.casestudy.source.FetchResponse

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
data class Response(val fetchResponse: FetchResponse?,
                    val fetchError: FetchError?) {
}
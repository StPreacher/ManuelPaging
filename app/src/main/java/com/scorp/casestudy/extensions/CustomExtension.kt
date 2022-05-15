package com.scorp.casestudy.extensions

import com.scorp.casestudy.source.Person

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
fun List<Person>.removeSameItemsAndAddAll(otherList: List<Person>): List<Person> {
    val willRemoveItems: ArrayList<Person> = arrayListOf()
    val cleanList: ArrayList<Person> = this as ArrayList<Person>
    if (!this.isNullOrEmpty()) {
        for (item in this) {
            for (otherItem in otherList) {
                if (item.id == otherItem.id) {
                    willRemoveItems.add(item)
                }
            }
        }
        cleanList.addAll(otherList)
        cleanList.removeAll(willRemoveItems)
    } else {
        cleanList.addAll(otherList)
    }
    return cleanList.toList()
}

fun List<Person>.removeSameItems(): List<Person> {
    return this.distinctBy { it.id }
}

fun List<Person>.clear() {
    (this as ArrayList<Person>).clear()
}
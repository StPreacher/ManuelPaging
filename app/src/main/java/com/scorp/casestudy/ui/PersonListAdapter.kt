package com.scorp.casestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.scorp.casestudy.R
import com.scorp.casestudy.databinding.ItemPersonBinding
import com.scorp.casestudy.extensions.clear
import com.scorp.casestudy.extensions.removeSameItemsAndAddAll
import com.scorp.casestudy.source.Person

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
class PersonListAdapter() :
    RecyclerView.Adapter<PersonViewHolder>() {

    private var personList: List<Person> = arrayListOf()

    fun updateList(listToBeAdded: List<Person>) {
        personList.removeSameItemsAndAddAll(listToBeAdded)
    }

    fun clearList() {
        personList.clear()
    }

    fun getPersonList(): List<Person> {
        return personList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPersonBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_person, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount() = personList.size
}
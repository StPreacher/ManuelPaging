package com.scorp.casestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.scorp.casestudy.R
import com.scorp.casestudy.databinding.ItemPersonBinding
import com.scorp.casestudy.source.Person

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
class PersonListAdapter(private var userList: List<Person>) :
    RecyclerView.Adapter<PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPersonBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_person, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size
}
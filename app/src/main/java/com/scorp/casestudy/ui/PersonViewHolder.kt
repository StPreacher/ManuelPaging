package com.scorp.casestudy.ui

import androidx.recyclerview.widget.RecyclerView
import com.scorp.casestudy.databinding.ItemPersonBinding
import com.scorp.casestudy.source.Person

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
class PersonViewHolder(private val mBinding: ItemPersonBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    fun bind(item: Person) {
        mBinding.person = item
    }
}
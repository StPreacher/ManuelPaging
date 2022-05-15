package com.scorp.casestudy.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.scorp.casestudy.R
import com.scorp.casestudy.base.BaseActivity
import com.scorp.casestudy.databinding.ActivityPersonListBinding
import com.scorp.casestudy.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Mucahid Dogan
 * @since 14.05.22
 *
 * mucahidd3@gmail.com
 */
@AndroidEntryPoint
class PersonListActivity : BaseActivity<ActivityPersonListBinding>() {

    private val mViewModel: PersonViewModel by viewModels()

    private lateinit var adapter: PersonListAdapter

    companion object {
        const val TAG = "UserListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_person_list)

        mBinding.pullToRefreshLayout.setOnRefreshListener {
            //TODO update list
            Log.d(TAG, "refresh layout")
        }

        mViewModel.getPersonList(null).observe(this) {
            if (it.fetchResponse?.people != null && it.fetchResponse.people.isNotEmpty()) {
                adapter = PersonListAdapter(it.fetchResponse.people)
                mBinding.userListRV.adapter = adapter
            }
        }

    }

}
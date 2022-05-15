package com.scorp.casestudy.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import com.scorp.casestudy.R
import com.scorp.casestudy.base.BaseActivity
import com.scorp.casestudy.databinding.ActivityPersonListBinding
import com.scorp.casestudy.extensions.hide
import com.scorp.casestudy.extensions.notifyDataWithSaveState
import com.scorp.casestudy.extensions.show
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

    private var isLoading: Boolean = false

    private var next: String = ""

    companion object {
        const val TAG = "UserListActivity"

        const val PAGE_MIN_ITEM_SIZE = 13
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PersonListAdapter()
        bindView(R.layout.activity_person_list)
        loadPersonList(null, false)
        observe()
        mBinding.userListRV.adapter = adapter
        mBinding.userListRV.itemAnimator = null
        mBinding.scrollView.setOnScrollChangeListener { view: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == view.getChildAt(0).measuredHeight - view.measuredHeight) {
                loadPersonList(next, false)
            }
        }

        mBinding.pullToRefreshLayout.setOnRefreshListener {
            //TODO update list
            Log.d(TAG, "refresh layout")
            adapter.clearList()
            loadPersonList(null, true)
        }

    }

    private fun observe() {
        mViewModel.response.observe(this) {
            isLoading = false
            mBinding.pullToRefreshLayout.isRefreshing = false
            if (!it.fetchError?.errorDescription.isNullOrEmpty()) {
                mBinding.userListRV.hide()
                mBinding.errorMsg.show()
                mBinding.setErrorMsg(it.fetchError?.errorDescription)
                mBinding.progressBar.hide()
            } else {
                it.fetchResponse?.next?.let { nextStr ->
                    next = nextStr
                }
                if (it.fetchResponse?.people != null && it.fetchResponse.people.isNotEmpty()) {
                    adapter.updateList(it.fetchResponse.people)
                    mBinding.userListRV.notifyDataWithSaveState(adapter)
                    if (adapter.getPersonList().size < PAGE_MIN_ITEM_SIZE) {
                        mBinding.userListRV.hide()
                        mBinding.errorMsg.hide()
                        mBinding.progressBar.show()
                        loadPersonList(next, false)
                    } else {
                        mBinding.userListRV.show()
                        mBinding.errorMsg.hide()
                        mBinding.progressBar.hide()
                    }
                }
            }
        }
    }

    private fun loadPersonList(next: String?, isFromRefresh: Boolean) {
        isLoading = true
        if (!isFromRefresh) {
            mBinding.progressBar.show()
        } else {
            mBinding.progressBar.hide()
        }
        mViewModel.loadPersonList(next, isFromRefresh)
    }

}
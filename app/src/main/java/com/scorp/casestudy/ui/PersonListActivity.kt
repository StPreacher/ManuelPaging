package com.scorp.casestudy.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import com.scorp.casestudy.R
import com.scorp.casestudy.SharedPreUtil
import com.scorp.casestudy.base.BaseActivity
import com.scorp.casestudy.const.Constant
import com.scorp.casestudy.databinding.ActivityPersonListBinding
import com.scorp.casestudy.extensions.hide
import com.scorp.casestudy.extensions.notifyDataWithSaveState
import com.scorp.casestudy.extensions.removeSameItems
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

    private var adapter: PersonListAdapter = PersonListAdapter()

    private var next: String = ""

    companion object {
        const val TAG = "UserListActivity"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val lastRefreshTime: Long = SharedPreUtil.getLong(
                Constant.STORE_REFRESH_TIME_KEY,
                Constant.STORE_REFRESH_DEF_VALUE,
                baseContext
            )
            val currentTime: Long = System.currentTimeMillis()
            if ((lastRefreshTime == Constant.STORE_REFRESH_DEF_VALUE) || currentTime - lastRefreshTime >= Constant.REFRESH_INTERVAL) {
                SharedPreUtil.putLong(Constant.STORE_REFRESH_TIME_KEY, currentTime, baseContext)
                adapter.clearList()
                loadPersonList(null, true)
            } else {
                mBinding.pullToRefreshLayout.isRefreshing = false
                Toast.makeText(
                    baseContext,
                    "You must wait 10 seconds after your last refresh",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun observe() {
        mViewModel.response.observe(this) {
            mBinding.pullToRefreshLayout.isRefreshing = false
            if (!it.fetchResponse?.people.isNullOrEmpty()) {
                it.fetchResponse?.let { response -> adapter.updateList(response.people.removeSameItems()) }
                it.fetchResponse?.next?.let { nextStr -> next = nextStr }
                mBinding.userListRV.notifyDataWithSaveState(adapter)
                if (adapter.getPersonList().size < Constant.PAGE_MIN_ITEM_SIZE) {
                    mBinding.userListRV.hide()
                    mBinding.errorMsg.hide()
                    mBinding.progressBar.show()
                    loadPersonList(next, false)
                } else {
                    mBinding.userListRV.show()
                    mBinding.errorMsg.hide()
                    mBinding.progressBar.hide()
                }
            } else if (!it.fetchError?.errorDescription.isNullOrEmpty()) {
                mBinding.userListRV.hide()
                mBinding.errorMsg.show()
                mBinding.setErrorMsg(it.fetchError?.errorDescription)
                mBinding.progressBar.hide()
            } else {
                mBinding.userListRV.hide()
                mBinding.errorMsg.show()
                mBinding.setErrorMsg("An unknown error has occurred")
                mBinding.progressBar.hide()
            }

        }
    }

    private fun loadPersonList(next: String?, isFromRefresh: Boolean) {
        if (!isFromRefresh) {
            mBinding.progressBar.show()
        } else {
            mBinding.progressBar.hide()
        }
        mViewModel.loadPersonList(next, isFromRefresh)
    }

}
package com.scorp.casestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.scorp.casestudy.R
import com.scorp.casestudy.base.BaseActivity
import com.scorp.casestudy.databinding.ActivityUserListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Mucahid Dogan
 * @since 14.05.22
 *
 * mucahidd3@gmail.com
 */
@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {

    companion object {
        const val TAG = "UserListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_user_list)
        mBinding.pullToRefreshLayout.setOnRefreshListener {
            //TODO update list
            Log.d(TAG, "refresh layout")
        }
    }

}
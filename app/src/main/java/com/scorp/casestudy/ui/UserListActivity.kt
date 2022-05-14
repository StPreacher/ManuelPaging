package com.scorp.casestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorp.casestudy.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Mucahid Dogan
 * @since 14.05.22
 *
 * mucahidd3@gmail.com
 */
@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
    }

}
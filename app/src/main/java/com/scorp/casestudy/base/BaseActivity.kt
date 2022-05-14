package com.scorp.casestudy.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Mucahid Dogan
 * @since 14.05.22
 *
 * mucahidd3@gmail.com
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var mBinding: T

    protected fun bindView(@LayoutRes layoutId: Int) {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
    }

}
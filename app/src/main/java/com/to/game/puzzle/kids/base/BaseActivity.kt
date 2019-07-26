package com.to.game.puzzle.kids.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Common base activity
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onPreSetContentView(savedInstanceState)
        setContentView(getResourceLayoutId())
        initView(savedInstanceState)
        initData(savedInstanceState)
        val action: String? = intent.action
    }

    /**
     * @return resource layout id
     */
    protected abstract fun getResourceLayoutId(): Int

    /**
     * call before setContentView() called
     * @see BaseActivity.onCreate
     */
    protected open fun onPreSetContentView(savedInstanceState: Bundle?) {

    }

    /**
     * call after setContentView() called
     * @see BaseActivity.onCreate
     */
    protected open fun initView(savedInstanceState: Bundle?) {

    }

    /**
     * call after setContentView() called
     * @see BaseActivity.onCreate
     */
    protected open fun initData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        var ret = false
        val fragment = getVisibleFragment()
        if (fragment is BaseFragment) {
            ret = fragment.onBackPressed()
        }
        if (!ret) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                super.onBackPressed()
            } else {
                finish()
            }
        }
    }

    /**
     * find visible fragment
     */
    open fun getVisibleFragment(): androidx.fragment.app.Fragment? {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        for (i in fragments.indices.reversed()) {
            val fragment = fragments[i]
            if (fragment.isVisible) {
                return fragment
            }
        }
        return null
    }
}
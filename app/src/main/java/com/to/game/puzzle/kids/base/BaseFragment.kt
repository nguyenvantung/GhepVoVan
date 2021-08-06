package com.to.game.puzzle.kids.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    /**
     * @return resource layout id
     */
    protected abstract fun getResourceLayoutId(): Int

    /**
     * init data
     */
    protected open fun initView(savedInstanceState: Bundle?) {

    }

    /**
     * init data
     */
    protected open fun initData(savedInstanceState: Bundle?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val resId = getResourceLayoutId()
        return inflater.inflate(resId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initData(savedInstanceState)
    }
    /**
     * handle event back click in this method
     * @return true if event consumed at here other default will handle at
     * @see [BaseActivity.onBackPressed]
     */
    open fun onBackPressed(): Boolean {
        return false
    }

    open fun popBackStack() {
        val fm = activity!!.supportFragmentManager
        val backStackCount = fm.backStackEntryCount
        if (backStackCount > 0) {
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}
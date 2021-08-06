package com.to.game.puzzle.kids.ui.activity

import android.os.Bundle
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.ui.fragment.SelectCategoryDrawFragment
import com.to.game.puzzle.kids.util.FragmentUtil

class DrawActivity: BaseActivity() {
    override fun getResourceLayoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        FragmentUtil.pushFragment(supportFragmentManager, SelectCategoryDrawFragment())
    }
}
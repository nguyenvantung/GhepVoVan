package com.to.game.puzzle.kids.ui.activity

import android.os.Bundle
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.ui.fragment.HomeFragment
import com.to.game.puzzle.kids.ui.fragment.PaintingFragment
import com.to.game.puzzle.kids.util.FragmentUtil


class MainActivity : BaseActivity() {

    override fun getResourceLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        FragmentUtil.replaceFragmentAndAddToBackStack(supportFragmentManager, HomeFragment())
    }
}

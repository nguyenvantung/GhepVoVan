package com.to.game.puzzle.kids.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.ui.activity.PuzzleActivity
import com.to.game.puzzle.kids.util.FragmentUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment() {
    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        buttonGhep.setOnClickListener {
            FragmentUtil.replaceFragmentAndAddToBackStack(activity!!, PaintingFragment.newInstance(), "")
        }

        buttonPuzzle.setOnClickListener {
            val intent = Intent(activity, PuzzleActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.to.game.puzzle.kids.ui.fragment

import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.ui.activity.BaseFragment

class SelectItemFragment: BaseFragment() {

    companion object{
        fun newInstance(item: Int): SelectItemFragment{
            return SelectItemFragment()
        }
    }

    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_select_item
    }
}
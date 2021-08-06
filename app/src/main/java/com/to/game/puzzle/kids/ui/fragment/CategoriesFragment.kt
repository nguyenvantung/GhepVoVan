package com.to.game.puzzle.kids.ui.fragment

import android.os.Bundle
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants.Companion.ANIMAL
import com.to.game.puzzle.kids.constants.AppConstants.Companion.CARS
import com.to.game.puzzle.kids.constants.AppConstants.Companion.FISH
import com.to.game.puzzle.kids.constants.AppConstants.Companion.FRUIT
import com.to.game.puzzle.kids.constants.AppConstants.Companion.PRINCESS
import com.to.game.puzzle.kids.constants.AppConstants.Companion.SANTA
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.synthetic.main.fragment_menu_categories.*

class CategoriesFragment: BaseFragment() {

    companion object{
        fun newInstance(): CategoriesFragment{
            return CategoriesFragment()
        }
    }
    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_menu_categories
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        animal.setOnClickListener { gotoItem(ANIMAL) }
        food.setOnClickListener { gotoItem(FRUIT) }
        santa.setOnClickListener { gotoItem(SANTA) }
        cart.setOnClickListener { gotoItem(CARS) }
        princess.setOnClickListener { gotoItem(PRINCESS) }
        fish.setOnClickListener { gotoItem(FISH) }
    }

    private fun gotoItem(type: Int){
        UiUtil.playTouch(activity!!)
        FragmentUtil.pushFragment(activity!!, SelectItemFragment.newInstance(type), "")
    }
}
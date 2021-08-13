package com.to.game.puzzle.kids.ui.fragment

import android.os.Bundle
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
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

    var isColoring = false

    companion object{
        const val KEY_SCREEN_TYPE = "KEY_SCREEN_TYPE"
        fun newInstance(isColoring: Boolean): CategoriesFragment{
            val fragment = CategoriesFragment()
            val bundle = Bundle()
            bundle.putBoolean(KEY_SCREEN_TYPE, isColoring)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_menu_categories
    }

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            isColoring = it.getBoolean(KEY_SCREEN_TYPE, false)
        }

        super.initView(savedInstanceState)
        animal.setOnClickListener { gotoItem(ANIMAL) }
        food.setOnClickListener { gotoItem(FRUIT) }
        santa.setOnClickListener { gotoItem(SANTA) }
        cart.setOnClickListener { gotoItem(CARS) }
        princess.setOnClickListener { gotoItem(PRINCESS) }
        fish.setOnClickListener { gotoItem(FISH) }
    }

    private fun gotoItem(type: Int){
        activity?.let {
            UiUtil.playTouch(it)
            if(isColoring){
                FragmentUtil.pushFragment(it, SelectItemDrawFragment.newInstance(type), "")
            }else{
                FragmentUtil.pushFragment(it, SelectItemFragment.newInstance(type), "")
            }
        }

    }
}
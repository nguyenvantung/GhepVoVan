package com.to.game.puzzle.kids.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.ui.activity.PuzzleActivity
import com.to.game.puzzle.kids.ui.adapter.OnCLickItemInterface
import com.to.game.puzzle.kids.ui.adapter.SelectImageAdapter
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.synthetic.main.fragment_menu_categories.*
import kotlinx.android.synthetic.main.fragment_select_item.*
import java.io.IOException

class SelectItemFragment: BaseFragment() {
    private var type = 0
    var path = "ani"
    private var listImage: MutableList<String> = arrayListOf()

    companion object{
        const val KEY_TYPE_CATEGORIES = "key_type_categories"
        fun newInstance(item: Int): SelectItemFragment{
            val bundle = Bundle()
            bundle.putInt(KEY_TYPE_CATEGORIES, item)
            val selectItemFragment = SelectItemFragment()
            selectItemFragment.arguments = bundle
            return selectItemFragment
        }
    }

    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_select_item
    }

    @SuppressLint("WrongConstant")
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        type = arguments!!.getInt(KEY_TYPE_CATEGORIES)
        listImage = getListImage(type)
        rclListItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rclListItem.setHasFixedSize(true)
        val selectImageAdapter = SelectImageAdapter(path, listImage, object : OnCLickItemInterface{
            override fun onClickItem(pathImage: String) {
                UiUtil.playTouch(activity!!)
                val intent = Intent(activity, PuzzleActivity::class.java)
                intent.putExtra(AppConstants.KEY_IMAGE_PUZZLE, pathImage)
                startActivity(intent)
                //FragmentUtil.replaceFragmentAndAddToBackStack(activity!!, PuzzleFragment.newInstance(pathImage), "")
            }
        })
        AppConstants.horizontalResolution = 3
        AppConstants.verticalResolution = 3
        rclListItem.adapter = selectImageAdapter
        tvSetting3.setOnClickListener {
            setDefaultButton()
            tvSetting3.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 3
            AppConstants.verticalResolution = 3
        }
        tvSetting4.setOnClickListener {
            setDefaultButton()
            tvSetting4.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 4
            AppConstants.verticalResolution = 4
        }
        tvSetting5.setOnClickListener {
            setDefaultButton()
            tvSetting5.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 5
            AppConstants.verticalResolution = 5
        }
        tvSetting6.setOnClickListener {
            setDefaultButton()
            tvSetting6.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 6
            AppConstants.verticalResolution = 6
        }
        tvSetting7.setOnClickListener {
            setDefaultButton()
            tvSetting7.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 7
            AppConstants.verticalResolution = 7
        }
        tvSetting8.setOnClickListener {
            setDefaultButton()
            tvSetting8.setBackgroundResource(R.drawable.bg_setting_pie_select)
            AppConstants.horizontalResolution = 8
            AppConstants.verticalResolution = 8
        }

        imBackMenu.setOnClickListener {
            popBackStack()
        }
    }

    private fun getListImage(type: Int): MutableList<String>{

        when(type){
            AppConstants.ANIMAL -> path = "ani"
            AppConstants.CARS -> path = "car"
            AppConstants.SANTA -> path = "disney"
            AppConstants.FISH -> path = "fish"
            AppConstants.PRINCESS -> path = "princess"
            AppConstants.FRUIT -> path = "food"
        }

        var list: Array<String>? = null
        try {
            list = activity!!.assets.list(path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list!!.toMutableList()
    }

    private fun setDefaultButton(){
        tvSetting3.setBackgroundResource(R.drawable.bg_setting_pie)
        tvSetting4.setBackgroundResource(R.drawable.bg_setting_pie)
        tvSetting5.setBackgroundResource(R.drawable.bg_setting_pie)
        tvSetting6.setBackgroundResource(R.drawable.bg_setting_pie)
        tvSetting7.setBackgroundResource(R.drawable.bg_setting_pie)
        tvSetting8.setBackgroundResource(R.drawable.bg_setting_pie)
    }



}
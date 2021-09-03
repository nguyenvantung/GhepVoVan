package com.to.game.puzzle.kids.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.`interface`.OnClickItem
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.ui.activity.ColorActivity
import com.to.game.puzzle.kids.ui.adapter.SelectItemDrawAdapter
import com.to.game.puzzle.kids.util.UiUtil
import com.to.game.puzzle.kids.view.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_choise.*
import kotlinx.android.synthetic.main.fragment_choise.imBackMenu
import java.io.IOException

class SelectItemDrawFragment : BaseFragment(), OnClickItem{

    private var interstitialAd: InterstitialAd? = null
    private val isShowFirst = 0
    private var option = 0
    private var item = ""
    private var isShowADS = false
    companion object{
        fun newInstance(option: Int): SelectItemDrawFragment {
            val bundle = Bundle()
            bundle.putInt(AppConstants.KEY_OPTION, option)
            val selectItemFragment = SelectItemDrawFragment()
            selectItemFragment.arguments = bundle
            return selectItemFragment
        }
    }

    override fun getResourceLayoutId(): Int =  R.layout.fragment_choise

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val adRequest = AdRequest.Builder().build()
        adBanner.loadAd(adRequest)
        activity?.let {
            listItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val itemDecoration = ItemOffsetDecoration(it, R.dimen.size_5)
            listItem.addItemDecoration(itemDecoration)
        }
        imBackMenu.setOnClickListener {
            popBackStack()
        }

    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        arguments?.let {
            option = it.getInt(AppConstants.KEY_OPTION)
            val adapter = SelectItemDrawAdapter(getDataList(option), activity!!, item)
            listItem.adapter = adapter
            adapter.setOnClickItem(this)
        }
    }


    private fun getDataList(position: Int): Array<String> {
        var integerList: Array<String> = arrayOf()
        when (position) {
            AppConstants.ANIMAL -> {
                item = "coloring/animal"
                integerList = getFileItem("coloring/animal")
            }
            AppConstants.CARS -> {
                item = "coloring/car"
                integerList = getFileItem("coloring/car")
            }
            AppConstants.FRUIT -> {
                item = "coloring/food"
                integerList = getFileItem("coloring/food")
            }
            AppConstants.PRINCESS -> {
                item = "coloring/mickey"
                integerList = getFileItem("coloring/mickey")
            }
            AppConstants.FISH -> {
                item = "coloring/princesses"
                integerList = getFileItem("coloring/princesses")
            }
            AppConstants.SANTA -> {
                item = "coloring/santa"
                integerList = getFileItem("coloring/santa")
            }
        }
        return integerList
    }

    private fun getFileItem(path: String): Array<String> {
        var list: Array<String> = arrayOf()
        try {
            list = activity!!.assets.list(path)?: arrayOf()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list
    }



    override fun onPause() {
        if (adBanner != null) {
            adBanner.pause()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        /*if (adView != null) {
            adView.resume();
        }
        if (isShowADS){
            isShowADS = false;
            isShowFirst = 1;
            showInterstitial();
        }*/
    }

    override fun onDestroy() {
        if (adBanner != null) {
            adBanner.destroy()
        }
        super.onDestroy()
    }

    override fun onClick(fileName: String) {
        val intent = Intent(activity, ColorActivity::class.java)
        intent.putExtra(AppConstants.KEY_DRAWABLE, fileName)
        startActivity(intent)
        UiUtil.playSong(activity, R.raw.sf_0)
        isShowADS = true
    }
}
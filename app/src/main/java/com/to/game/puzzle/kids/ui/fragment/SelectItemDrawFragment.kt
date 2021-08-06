package com.to.game.puzzle.kids.ui.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.`interface`.OnClickItem
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.ui.activity.ColorActivity
import com.to.game.puzzle.kids.ui.adapter.SelectItemDrawAdapter
import com.to.game.puzzle.kids.util.UiUtil
import com.to.game.puzzle.kids.view.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_choise.*
import kotlinx.android.synthetic.main.layout_toolbar.*
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
        activity?.let {
            listItem.layoutManager = GridLayoutManager(it, 2)
            val itemDecoration = ItemOffsetDecoration(it, R.dimen.size_5)
            listItem.addItemDecoration(itemDecoration)
            val font1 = Typeface.createFromAsset(activity!!.assets, "coloring/fonts/cooper_black.ttf")
            title.typeface = font1
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

        setUpAdmob()
        showAdViewFull()
    }

    private fun showAdViewFull() {
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = InterstitialAd(activity)
        // Defined in res/values/strings.xml
        interstitialAd?.let {it->
            it.setAdUnitId(AppConstants.ADMOB_UNIT_ID)
            val adRequest = AdRequest.Builder().build()
            it.loadAd(adRequest)
            it.setAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    if (isShowFirst > 0) {
                        it.show()
                    }
                }
            })
        }

    }

    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        interstitialAd?.let {
            if(it.isLoaded){
                it.show()
            }
        }
    }

    private fun getDataList(position: Int): Array<String> {
        var integerList: Array<String> = arrayOf()
        when (position) {
            AppConstants.AMINAL -> {
                item = "coloring/animal"
                integerList = getFileItem("coloring/animal")
                title.text = "Animal"
            }
            AppConstants.CARS -> {
                item = "coloring/car"
                integerList = getFileItem("coloring/car")
                title.text = "Cars"
            }
            AppConstants.FOOD -> {
                item = "coloring/food"
                integerList = getFileItem("coloring/food")
                title.text = "Food"
            }
            AppConstants.MICKEY -> {
                item = "coloring/mickey"
                integerList = getFileItem("coloring/mickey")
                title.text = "Mickey"
            }
            AppConstants.MERMAIDS -> {
                item = "coloring/princesses"
                integerList = getFileItem("coloring/princesses")
                title.text = "People"
            }
            AppConstants.SANTA -> {
                item = "coloring/santa"
                integerList = getFileItem("coloring/santa")
                title.text = "Santa"
            }
            else -> title.text = "Animal"
        }
        return integerList
    }

    private fun getFileItem(path: String): Array<String> {
        var list: Array<String> = arrayOf()
        try {
            list = activity!!.assets.list(path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list
    }

    private fun setUpAdmob() {
        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()

        // Start loading the ad in the background.
        bannerAds.loadAd(adRequest)
        bannerAds.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                Log.e("load ads", "onAdFailedToLoad:$errorCode")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }


    override fun onPause() {
        if (bannerAds != null) {
            bannerAds.pause()
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
        if (bannerAds != null) {
            bannerAds.destroy()
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
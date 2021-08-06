package com.to.game.puzzle.kids.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.synthetic.main.fragment_select_option.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SelectCategoryDrawFragment: BaseFragment(), View.OnClickListener {

    override fun getResourceLayoutId(): Int  = R.layout.fragment_select_option

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val font1 = Typeface.createFromAsset(activity!!.assets, "coloring/fonts/cooper_black.ttf")
        title.typeface = font1
        showAdViewBanner()
        itemAnimal.setOnClickListener(this)
        itemCar.setOnClickListener(this)
        itemMickey.setOnClickListener(this)
        itemSatan.setOnClickListener(this)
        itemMermaids.setOnClickListener(this)
        itemFruit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        activity?.let {activity->
            when(v?.id){
                R.id.itemAnimal ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.ANIMAL), "")
                }
                R.id.itemCar ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.CARS), "")
                }
                R.id.itemMickey ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.PRINCESS), "")
                }
                R.id.itemSatan ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.SANTA), "")
                }
                R.id.itemMermaids ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.FISH), "")
                }
                R.id.itemFruit ->{
                    FragmentUtil.pushFragment(activity, SelectItemDrawFragment.newInstance(AppConstants.FRUIT), "")
                }
            }
            UiUtil.playSong(activity, R.raw.z_textures_menu)
        }

    }

    private fun showAdViewBanner() {
        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()

        // Start loading the ad in the background.
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
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

    override fun onResume() {
        super.onResume()
        if (adView != null) {
            adView.resume()
        }
    }

    override fun onPause() {
        if (adView != null) {
            adView.pause()
        }
        super.onPause()
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        super.onDestroy()
    }
}
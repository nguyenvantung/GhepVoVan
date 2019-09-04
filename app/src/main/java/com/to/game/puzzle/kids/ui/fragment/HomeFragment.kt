package com.to.game.puzzle.kids.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.ui.activity.MainActivity
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.util.PreferenceHelper
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Appendable

class HomeFragment: BaseFragment() {
    var linkApp : String = ""
    private var isPlaySound = AppConstants.KEY_PLAY_SOUND
    override fun getResourceLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        val appPackageName = activity!!.packageName
        linkApp = "https://play.google.com/store/apps/details?id=$appPackageName"

        buttonGhep.setOnClickListener {
            FragmentUtil.replaceFragmentAndAddToBackStack(activity!!, PaintingFragment.newInstance(), "")
            UiUtil.playTouch(activity!!)
        }

        buttonPuzzle.setOnClickListener {
            UiUtil.playTouch(activity!!)
            FragmentUtil.replaceFragmentAndAddToBackStack(activity!!, CategoriesFragment.newInstance(), "")
        }

        handleShowSound(isPlaySound)
        ivSound.setOnClickListener {
            isPlaySound = !isPlaySound
            AppConstants.KEY_PLAY_SOUND = isPlaySound
            handleShowSound(isPlaySound)
            (activity as MainActivity).playSound(isPlaySound)
        }

        ivShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, linkApp)
            startActivity(sendIntent)
        }
        ivMore.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=MinToTet")))
            } catch (an: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkApp)))
            }
        }
        ivRate.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (an: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkApp)))
            }
        }
    }

    private fun handleShowSound(isPlay: Boolean){
        if (isPlay){
            ivSound.setImageResource(R.drawable.ic_sound_on)
        }else{
            ivSound.setImageResource(R.drawable.ic_sound_off)
        }
    }
}
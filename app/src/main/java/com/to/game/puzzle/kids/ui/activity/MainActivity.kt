package com.to.game.puzzle.kids.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.ui.fragment.HomeFragment
import com.to.game.puzzle.kids.ui.fragment.PaintingFragment
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.util.PreferenceHelper
import org.jsoup.Jsoup


class MainActivity : BaseActivity() {

    var mediaPlayer: MediaPlayer? = null
    private var currentVersion: String? = null

    override fun getResourceLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        FragmentUtil.replaceFragmentAndAddToBackStack(supportFragmentManager, HomeFragment())
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer!!.start()
        mediaPlayer!!.isLooping = true
        try {
            currentVersion = packageManager.getPackageInfo(packageName, 0).versionName
            GetVersionCode().execute()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }

    fun playSound(open: Boolean) {
        if (open) {
            mediaPlayer!!.start()
        } else {
            mediaPlayer!!.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        playSound(AppConstants.KEY_PLAY_SOUND)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
    }

    private inner class GetVersionCode : AsyncTask<Void, String, String>() {
        override fun doInBackground(vararg voids: Void): String? {

            var newVersion: String? = null
            try {
                newVersion =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=" + this@MainActivity.packageName + "&hl=it")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div[itemprop=softwareVersion]")
                        .first()
                        .ownText()
                return newVersion
            } catch (e: Exception) {
                return newVersion
            }

        }

        override fun onPostExecute(onlineVersion: String?) {
            super.onPostExecute(onlineVersion)
            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (java.lang.Float.valueOf(currentVersion) < java.lang.Float.valueOf(onlineVersion)) {
                    handleShowUpdate()
                }
            }
            Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion)
        }

    }

    private fun handleShowUpdate() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.update_version_title)
        builder.setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
        builder.setPositiveButton(R.string.yes) { dialog, _ ->
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + this@MainActivity.packageName)
                    )
                )
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + this@MainActivity.packageName)
                    )
                )
            }

            dialog.dismiss()
        }

        builder.create().show()
    }
}

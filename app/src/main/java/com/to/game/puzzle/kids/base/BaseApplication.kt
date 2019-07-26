package com.to.game.puzzle.kids.ui.activity

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import java.lang.ref.WeakReference

open class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    companion object {

        private var instance: BaseApplication? = null

        /**
         * get instance of [Application]
         */
        fun getApplication(): BaseApplication? {
            return instance
        }

        /**
         * get string from resource id
         */
        fun getString(stringResId: Int): String {
            return instance!!.getString(stringResId)
        }

        /**
         * get string from resource if with format data
         */
        fun getString(stringResId: Int, vararg formatArgs: Any?): String {
            return instance!!.getString(stringResId, formatArgs)
        }
    }

    /**
     * a weak ref to [Activity]
     * use weak ref to avoid leak [Activity]
     */
    private var mRefActivity: WeakReference<Activity>? = null

    /**
     * count number activity resumed
     */
    private var resumedCount = 0L
    /**
     * count number activity paused
     */
    private var pausedCount = 0L

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityPaused(activity: Activity?) {
        pausedCount++
    }

    override fun onActivityResumed(activity: Activity?) {
        mRefActivity = WeakReference(activity!!)
        resumedCount++
    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }

    /**
     * get current active activity or null
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getActiveActivity(): Activity? {
        val activity = mRefActivity?.get()
        val ret = (activity?.isFinishing ?: false) or (activity?.isDestroyed ?: false)
        return if (!ret) activity else null
    }

    /**
     * check app is in foreground or background
     */
    fun isAppForeground() : Boolean {
        return resumedCount > pausedCount
    }

}
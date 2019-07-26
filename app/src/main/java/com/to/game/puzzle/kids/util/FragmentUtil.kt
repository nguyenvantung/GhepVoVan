package com.to.game.puzzle.kids.util

import android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.to.game.puzzle.kids.R

object FragmentUtil {
    /**
     * create new instance of [Fragment]
     *
     * @return instance
     */
    fun <T : Fragment> newInstance(clazz: Class<T>, args: Bundle): T? {
        try {
            val t = clazz.newInstance()
            t.arguments = args
            return t
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }

    fun popBackStack(activity: FragmentActivity?, tag: String?) {
        if (tag != null && activity != null) {
            activity.supportFragmentManager.popBackStack(tag, POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun popBackStackImmediate(activity: FragmentActivity?, tag: String?) {
        if (tag != null && activity != null) {
            activity.supportFragmentManager.popBackStackImmediate(tag, POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun popBackStack(activity: FragmentActivity?) {
        activity?.supportFragmentManager?.popBackStack()
    }

    fun popBackStackImmediate(activity: FragmentActivity?) {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

    fun popBackStack(manager: FragmentManager?, tag: String?) {
        if (tag != null && manager != null) {
            manager.popBackStack(tag, POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun popBackStackImmediate(manager: FragmentManager?, tag: String?) {
        if (tag != null && manager != null) {
            manager.popBackStackImmediate(tag, POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun popBackStack(manager: FragmentManager?) {
        manager?.popBackStack()
    }

    fun popBackStackImmediate(manager: FragmentManager?) {
        manager?.popBackStackImmediate()
    }

    fun popAllBackStack(activity: FragmentActivity?) {
        if (activity == null) {
            return
        }
        val fm = activity.supportFragmentManager
        val backStackCount = fm.backStackEntryCount
        for (i in 0 until backStackCount) {
            fm.popBackStack()
        }
    }

    /**
     * replace fragment but no add to back stack
     */
    fun replaceFragment(manager: FragmentManager, fragment: Fragment) {
        replaceFragment(manager, fragment, null, false, false)
    }


    fun replaceFragment(activity: FragmentActivity, fragment: Fragment) {
        replaceFragment(activity.supportFragmentManager, fragment)
    }

    fun replaceFragmentAndAddToBackStack(
        activity: FragmentActivity, fragment: Fragment,
        tag: String?
    ) {
        replaceFragment(activity.supportFragmentManager, fragment, tag, false, true)
    }

    fun replaceFragmentAndAddToBackStack(
        manager: FragmentManager, fragment: Fragment,
        tag: String?
    ) {
        replaceFragment(manager, fragment, tag, false, true)
    }

    fun replaceFragmentAndAddToBackStack(manager: FragmentManager?, fragment: Fragment) {
        replaceFragment(manager, fragment, fragment.javaClass.name, false, true)
    }


    fun replaceFragment(
        manager: FragmentManager?, fragment: Fragment,
        tag: String?, isShowAnimation: Boolean, addToBackStack: Boolean
    ) {


        val fragmentTransaction = manager!!.beginTransaction()
        if (isShowAnimation) {
            fragmentTransaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out, R.anim.fade_in, R.anim.fade_out
            )
        }
        fragmentTransaction.replace(R.id.container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun getCurrentFragmentByTag(activity: FragmentActivity, tag: String): Fragment? {
        return getCurrentFragmentByTag(activity.supportFragmentManager, tag)
    }

    fun getCurrentFragmentByTag(fragmentManager: FragmentManager, tag: String): Fragment? {
        return fragmentManager.findFragmentByTag(tag)
    }


    fun clearAllFragments(fragmentManager: FragmentManager): Boolean {
        val fragments = fragmentManager.fragments
        for (f in fragments) {
            if (f != null) {
                fragmentManager.beginTransaction().remove(f).commit()
            }
        }
        return true
    }


}
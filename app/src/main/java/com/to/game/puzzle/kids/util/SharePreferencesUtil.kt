package com.to.game.puzzle.kids.util

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.to.game.puzzle.kids.PuzzleApplication


class SharePreferencesUtil{

    companion object {
        private fun getInstance(): SharedPreferences{
            return PreferenceManager.getDefaultSharedPreferences(PuzzleApplication.getApplication())
        }
        fun clearPreferences() {
            val editor = getInstance().edit()
            editor.clear()
            editor.apply()
        }

        fun putString(key: String, value: String){
            val editor = getInstance().edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun putInt(key: String, value: Int){
            val editor = getInstance().edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun putBoolean(key: String, value: Boolean){
            val editor = getInstance().edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getString(key: String){
            getInstance().getString(key,"")
        }

        fun getInt(key: String, deault: Int): Int{
            return getInstance().getInt(key,deault)
        }

        fun getBoolean(key: String, value: Boolean): Boolean{
            return getInstance().getBoolean(key,value)
        }
    }



}
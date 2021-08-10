package com.to.game.puzzle.kids.util

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.to.game.puzzle.kids.PuzzleApplication

class PreferenceHelper private constructor() {

    companion object {

        private fun getPreference(): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(PuzzleApplication.getApplication())
        }

        /**
         * save [Int] value
         */
        fun putInt(key: String, value: Int) {
            getPreference()
                .edit()
                .putInt(key, value)
                .apply()
        }

        /**
         * save Int array
         */
        fun putIntArray(keys: Array<String>, values: Array<Int>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putInt(keys[i], values[i])
            }
            editor.apply()
        }

        /**
         * save [Float] value
         */
        fun putFloat(key: String, value: Float) {
            getPreference()
                .edit()
                .putFloat(key, value)
                .apply()
        }

        /**
         * save Float array
         */
        fun putFloatArray(keys: Array<String>, values: Array<Float>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putFloat(keys[i], values[i])
            }
            editor.apply()
        }

        /**
         * save [Long] value
         */
        fun putLong(key: String, value: Long) {
            getPreference()
                .edit()
                .putLong(key, value)
                .apply()
        }

        /**
         * save Long array
         */
        fun putLongArray(keys: Array<String>, values: Array<Long>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putLong(keys[i], values[i])
            }
            editor.apply()
        }

        /**
         * put [Double] value
         */
        fun putDouble(key: String, value: Double) {
            putLong(key, java.lang.Double.doubleToLongBits(value))
        }

        /**
         * save Long array
         */
        fun putDoubleArray(keys: Array<String>, values: Array<Double>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putLong(keys[i], java.lang.Double.doubleToLongBits(values[i]))
            }
            editor.apply()
        }


        /**
         * save [String] value
         */
        fun putString(key: String, value: String) {
            getPreference()
                .edit()
                .putString(key, value)
                .apply()
        }

        /**
         * save String array
         */
        fun putStringArray(keys: Array<String>, values: Array<String>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putString(keys[i], values[i])
            }
            editor.apply()
        }

        /**
         * save [Boolean] value
         */
        fun putBoolean(key: String, value: Boolean) {
            getPreference()
                .edit()
                .putBoolean(key, value)
                .apply()
        }

        /**
         * save Boolean array
         */
        fun putBooleanArray(keys: Array<String>, values: Array<Boolean>) {
            if (keys.size != values.size) {
                throw IllegalArgumentException("error key array size != value array size")
            }
            val editor = getPreference().edit()
            for (i in 0 until keys.size) {
                editor.putBoolean(keys[i], values[i])
            }
            editor.apply()
        }

        /**
         * save [MutableSet]
         */
        fun putStringSet(key: String, value: MutableSet<String>) {
            getPreference()
                .edit()
                .putStringSet(key, value)
                .apply()
        }

        /**
         * get [Int] value
         */
        fun getInt(key: String, defaultValue: Int): Int {
            return getPreference().getInt(key, defaultValue)
        }

        /**
         * get Int array
         */
        fun getIntArray(keys: Array<String>): IntArray {
            val pref = getPreference()
            val ret = IntArray(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = pref.getInt(key, 0)
            }
            return ret
        }

        /**
         * get [Float] value
         */
        fun getFloat(key: String, defaultValue: Float): Float {
            return getPreference().getFloat(key, defaultValue)
        }

        /**
         * get float array
         */
        fun getFloatArray(keys: Array<String>): FloatArray {
            val pref = getPreference()
            val ret = FloatArray(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = pref.getFloat(key, 0f)
            }
            return ret
        }

        /**
         *  get [Long] value
         */
        fun getLong(key: String, defaultValue: Long): Long {
            return getPreference().getLong(key, defaultValue)
        }

        /**
         * get long array
         */
        fun getLongArray(keys: Array<String>): LongArray {
            val pref = getPreference()
            val ret = LongArray(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = pref.getLong(key, 0L)
            }
            return ret
        }

        /**
         * get double value
         */
        fun getDouble(key: String, defaultValue: Double): Double {
            val ret = getLong(key, Long.MIN_VALUE)
            return if (ret != Long.MIN_VALUE) java.lang.Double.longBitsToDouble(ret) else defaultValue
        }

        /**
         * get double array
         */
        fun getDoubleArray(keys: Array<String>): DoubleArray {
            val pref = getPreference()
            val ret = DoubleArray(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = java.lang.Double.longBitsToDouble(pref.getLong(key, 0L))
            }
            return ret
        }

        /**
         * get [String] value
         */
        fun getString(key: String, defaultValue: String): String {
            return getPreference().getString(key, defaultValue).toString()
        }

        /**
         * get String array
         */
        fun getStringArray(keys: Array<String>): Array<String?> {
            val pref = getPreference()
            val ret = arrayOfNulls<String>(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = pref.getString(key, null)
            }
            return ret
        }

        /**
         * get [Boolean] value
         */
        fun getBoolean(key: String, defaultValue: Boolean): Boolean {
            return getPreference().getBoolean(key, defaultValue)
        }

        /**
         * get boolean array
         */
        fun getBooleanArray(keys: Array<String>): BooleanArray {
            val pref = getPreference()
            val ret = BooleanArray(keys.size)
            for ((index, key) in keys.withIndex()) {
                ret[index] = pref.getBoolean(key, false)
            }
            return ret
        }

        /**
         * get [MutableSet]
         */
        fun getStringSet(key: String, defaultValue: MutableSet<String>) {
            getPreference().getStringSet(key, defaultValue)
        }

        fun removeDataPreference(key: String){
            getPreference().edit().remove(key).commit()
        }
    }
}
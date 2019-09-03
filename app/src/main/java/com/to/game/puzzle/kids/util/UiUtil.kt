package com.to.game.puzzle.kids.util

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream

class UiUtil {
    companion object{

        fun getDrawable(context: Context, path: String): Drawable?{
            var imageStream: InputStream? = null
            var drawable: Drawable? = null
            try {
                // get input stream
                imageStream = context.assets.open(path)
                // load image as Drawable
                drawable = Drawable.createFromStream(imageStream, null)
                // set image to ImageView
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            return drawable
        }
    }

}
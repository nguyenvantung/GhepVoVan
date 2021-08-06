package com.to.game.puzzle.kids.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import com.to.game.puzzle.kids.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

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

        fun playTouch(activity: Activity){
            val mediaPlayer = MediaPlayer.create(activity, R.raw.click)
            mediaPlayer.start()
        }

        fun playSong(context: Context?, sound: Int) {
            val mediaPlayer = MediaPlayer.create(context, sound)
            mediaPlayer.start()
        }

        fun overlay(bmp1: Bitmap, bmp2: Bitmap?): Bitmap? {
            val bmOverlay = Bitmap.createBitmap(bmp1.width, bmp1.height, bmp1.config)
            val canvas = Canvas(bmOverlay)
            canvas.drawBitmap(bmp1, Matrix(), null)
            canvas.drawBitmap(bmp2, 0f, 0f, null)
            return bmOverlay
        }

        fun getBitmapFromView(width: Int, height: Int, color: Int, context: Context?): Bitmap? {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bitmap.eraseColor(ContextCompat.getColor(context!!, color))
            } else {
                bitmap.eraseColor(color)
            }
            return bitmap
        }

        fun saveBitmap(bitmap: Bitmap): File? {
            return if (isExternalStorageWritable()) {
                saveImage(bitmap)
            } else null
        }

        fun saveImage(finalBitmap: Bitmap): File? {
            val myDir = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                ), "saved_images"
            )
            DebugLog.e("saveImage:" + myDir.mkdirs())
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fName = "kid$timeStamp.jpg"
            var file: File? = null
            try {
                myDir.mkdirs()
                if (!myDir.exists()) {
                    myDir.createNewFile()
                }
                file = File(myDir, fName)
                if (file.exists()) file.delete()
                file.createNewFile()
                val out = FileOutputStream(file)
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return file
        }

        /* Checks if external storage is available for read and write */
        fun isExternalStorageWritable(): Boolean {
            val state = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == state) {
                true
            } else false
        }
    }

}
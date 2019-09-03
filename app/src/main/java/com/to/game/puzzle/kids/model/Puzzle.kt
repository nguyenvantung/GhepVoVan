package com.to.game.puzzle.kids.model

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.widget.ImageView
import com.to.game.puzzle.kids.ui.activity.ImageCutter
import java.io.File
import java.util.*


class Puzzle {
    var mActivity: Activity? = null
    var mSourceImage: Bitmap? = null
    var puzzlePieceArrayList: ArrayList<PuzzlePiece> = arrayListOf()


    fun createPuzzlePieces(
            aActivity: Activity, width: Int, height: Int,
            imageView: ImageView, drawable: Drawable, path: String, horizontalResolution: Int, verticalResolution: Int
    ): ArrayList<PuzzlePiece> {
        this.mActivity = aActivity
        this.puzzlePieceArrayList = ArrayList()
        getDisplaySize(width, height, imageView, drawable)
        deleteDirectories(path)
        val cutMap = CutMap(horizontalResolution, verticalResolution)
        val imageCutter = mSourceImage?.let { ImageCutter(it, cutMap) }
        imageCutter?.let { drawOrderedPuzzlePieces(it, cutMap) }
        mSourceImage = null
        return puzzlePieceArrayList
    }

    private fun getDisplaySize(widthFinal: Int, heightFinal: Int, imageView: ImageView, drawable: Drawable) {
        var image: Bitmap? = null
        try {
            image = (drawable as BitmapDrawable).bitmap
            mSourceImage = Bitmap.createScaledBitmap(image!!, widthFinal, heightFinal, false)
        } catch (ex: OutOfMemoryError) {
            ex.printStackTrace()
        }
        mActivity!!.runOnUiThread { imageView.setImageBitmap(mSourceImage) }

    }

    private fun deleteDirectories(aPath: String) {
        val dir = File(Environment.getExternalStorageDirectory().toString() + aPath)
        if (dir.exists() && dir.isDirectory) {
            dir.delete()
            dir.mkdir()
        } else {
            dir.mkdir()
        }
    }

    private fun drawOrderedPuzzlePieces(imageCutter: ImageCutter, cutMap: CutMap) {
        val puzzlePieces = imageCutter.cutImage()
        for (i in 0 until cutMap.horizontalResolution) {
            for (j in 0 until cutMap.verticalResolution) {
                val piece = puzzlePieces[i][j]
                puzzlePieceArrayList.add(piece)
            }
        }
    }
}
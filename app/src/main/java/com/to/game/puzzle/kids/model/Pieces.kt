package com.to.game.puzzle.kids.model

import android.graphics.Bitmap

class Pieces {
    private var pX = 0
    private var pY = 0
    private var originalResource: Bitmap? = null
    private var position = 0

    fun getpX(): Int {
        return pX
    }

    fun setpX(pX: Int) {
        this.pX = pX
    }

    fun getpY(): Int {
        return pY
    }

    fun setpY(pY: Int) {
        this.pY = pY
    }

    fun getOriginalResource(): Bitmap? {
        return originalResource
    }

    fun setOriginalResource(originalResource: Bitmap) {
        this.originalResource = originalResource
    }

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }
}
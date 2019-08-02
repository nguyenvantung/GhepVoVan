package com.to.game.puzzle.kids.model

import android.graphics.Bitmap
import android.graphics.Point

class PuzzlePiece {
    var image: Bitmap? = null
    var anchorPoint: Point? = null
        private set
    var centerPoint: Point? = null
        private set
    var pieceCurveSet: PieceCurveSet? = null
        private set
    var width: Int = 0
        private set
    var height: Int = 0
        private set
    var path: String? = null

    constructor(
        image: Bitmap,
        path: String?,
        anchorPoint: Point,
        centerPoint: Point,
        pieceCurveSet: PieceCurveSet,
        width: Int,
        height: Int
    ) {
        this.image = image
        this.anchorPoint = anchorPoint
        this.centerPoint = centerPoint
        this.pieceCurveSet = pieceCurveSet
        this.width = width
        this.height = height
        this.path = path
    }

    constructor()

    constructor(
        path: String,
        anchorPoint: Point,
        centerPoint: Point,
        pieceCurveSet: PieceCurveSet,
        width: Int,
        height: Int
    ) {
        this.anchorPoint = anchorPoint
        this.centerPoint = centerPoint
        this.pieceCurveSet = pieceCurveSet
        this.width = width
        this.height = height
        this.path = path
    }
}

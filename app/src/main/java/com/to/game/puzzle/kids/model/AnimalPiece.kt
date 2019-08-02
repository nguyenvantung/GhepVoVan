package com.to.game.puzzle.kids.model

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView

class AnimalPiece(context: Context) : AppCompatImageView(context) {
    var xCoord: Int = 0
    var yCoord: Int = 0
    var pieceWidth: Int = 0
    var pieceHeight: Int = 0
    var canMove = true
}

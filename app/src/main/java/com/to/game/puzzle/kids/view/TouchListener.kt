package com.to.game.puzzle.kids.view

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.to.game.puzzle.kids.`interface`.ActionDoneInterface
import com.to.game.puzzle.kids.model.AnimalPiece


class TouchListener : OnTouchListener {
    private var xDelta = 0f
    private var yDelta = 0f
    private var fragment: ActionDoneInterface? = null
    private var piece: AnimalPiece? = null
    fun setFragment(fragment: ActionDoneInterface?) {
        this.fragment = fragment
    }

    fun setPiece(piece: AnimalPiece?) {
        this.piece = piece
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.rawX
        val y = motionEvent.rawY
        val tolerance = Math.sqrt(Math.pow(view.width.toDouble(),2.0) + Math.pow(view.height.toDouble(), 2.0)) / 10
        if (!piece!!.canMove) {
            return true
        }
        var lParams =  view.layoutParams as RelativeLayout.LayoutParams
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                xDelta = x - lParams.leftMargin
                yDelta = y - lParams.topMargin
                piece!!.bringToFront()
            }
            MotionEvent.ACTION_MOVE -> {
                lParams = view.layoutParams as RelativeLayout.LayoutParams
                lParams.leftMargin = (x - xDelta).toInt()
                lParams.topMargin = (y - yDelta).toInt()
                lParams.bottomMargin = -250
                lParams.rightMargin = -250
                view.layoutParams = lParams
                val xDiff = StrictMath.abs(piece!!.xCoord - lParams.leftMargin)
                val yDiff = StrictMath.abs(piece!!.yCoord - lParams.topMargin)
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    Log.e("TouchListener", "onTouch ok:" + motionEvent.rawX.toInt() + "=="+ motionEvent.rawY.toInt() + "=="+ piece!!.xCoord + "=="+ piece!!.yCoord)
                    piece!!.canMove = false
                    lParams.leftMargin = piece!!.xCoord
                    lParams.topMargin = piece!!.yCoord
                    view.layoutParams = lParams
                    fragment?.actionDone(view.id)
                    sendViewToBack(piece)
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    private fun sendViewToBack(child: View?) {
        if (child?.parent != null){
            val parent = child?.parent as ViewGroup
            if (null != parent) {
                parent.removeView(child)
                parent.addView(child, 0)
            }
        }

    }
}
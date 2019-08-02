package com.to.game.puzzle.kids.model

import android.graphics.Point
import android.graphics.Rect
import android.util.Pair

object PuzzlePieceBorderFinder {

    fun getBorders(pieceCurveSet: PieceCurveSet, startPoint: Point, horizontalStep: Int, verticalStep: Int): Rect {
        return Rect(
            leftmostPointForCurve(pieceCurveSet.left, startPoint, horizontalStep),
            topmostPointForCurve(pieceCurveSet.top, startPoint, verticalStep),
            rightmostPointForCurve(pieceCurveSet.right, startPoint, horizontalStep),
            bottommostPointForCurve(pieceCurveSet.bottom, startPoint, verticalStep)
        )
    }

    fun getOffsets(curveSet: PieceCurveSet, horizontalStep: Int, verticalStep: Int): Pair<Int, Int> {
        return Pair(getLeftOffset(curveSet.left, horizontalStep), getTopOffset(curveSet.top, verticalStep))
    }


    private fun topmostPointForCurve(curve: HorizontalCurve, startPoint: Point, verticalStep: Int): Int {
        if (curve == HorizontalCurve.NONE) {
            return startPoint.y
        }
        val scale = verticalStep / 100
        return if (curve == HorizontalCurve.TAB) {
            startPoint.y - 30 * scale
        } else {
            startPoint.y - 15 * scale
        }
    }

    private fun bottommostPointForCurve(curve: HorizontalCurve, startPoint: Point, verticalStep: Int): Int {
        if (curve == HorizontalCurve.NONE) {
            return startPoint.y + verticalStep
        }
        val scale = verticalStep / 100
        return if (curve == HorizontalCurve.TAB) {
            startPoint.y + verticalStep + 15 * scale
        } else {
            startPoint.y + verticalStep + 30 * scale
        }
    }

    private fun leftmostPointForCurve(curve: VerticalCurve, startPoint: Point, horizontalStep: Int): Int {
        if (curve === VerticalCurve.NONE) {
            return startPoint.x
        }
        val scale = horizontalStep / 100
        return if (curve === VerticalCurve.TAB) {
            startPoint.x - 30 * scale
        } else {
            startPoint.x - 15 * scale
        }
    }

    private fun rightmostPointForCurve(curve: VerticalCurve, startPoint: Point, horizontalStep: Int): Int {
        if (curve === VerticalCurve.NONE) {
            return startPoint.x + horizontalStep
        }
        val scale = horizontalStep / 100
        return if (curve === VerticalCurve.TAB) {
            startPoint.x + horizontalStep + 15 * scale
        } else {
            startPoint.x + horizontalStep + 30 * scale
        }
    }

    private fun getLeftOffset(curve: VerticalCurve, horizontalStep: Int): Int {
        if (curve === VerticalCurve.NONE) {
            return 0
        }
        val scale = horizontalStep / 100
        return if (curve === VerticalCurve.TAB) {
            30 * scale
        } else {
            15 * scale
        }
    }

    private fun getTopOffset(curve: HorizontalCurve, verticalStep: Int): Int {
        if (curve == HorizontalCurve.NONE) {
            return 0
        }
        val scale = verticalStep / 100
        return if (curve == HorizontalCurve.TAB) {
            30 * scale
        } else {
            15 * scale
        }
    }
}

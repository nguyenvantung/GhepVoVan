package com.to.game.puzzle.kids.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point

object CurveDrawer {

    private val horizontalTabCoords = arrayOf(
        Point(0, 0),
        Point(35, 15),
        Point(37, 5),
        Point(40, 0),
        Point(38, -5),
        Point(20, -20),
        Point(50, -20),
        Point(80, -20),
        Point(62, -5),
        Point(60, 0),
        Point(63, 5),
        Point(65, 15),
        Point(100, 0)
    )
    private val verticalTabCoords = arrayOf(
        Point(0, 0),
        Point(15, 35),
        Point(5, 37),
        Point(0, 40),
        Point(-5, 38),
        Point(-20, 20),
        Point(-20, 50),
        Point(-20, 80),
        Point(-5, 62),
        Point(0, 60),
        Point(5, 63),
        Point(15, 65),
        Point(0, 100)
    )

    fun drawPuzzlePiece(
        canvas: Canvas, pieceCurveSet: PieceCurveSet, startPoint: Point,
        horizontalStep: Int, verticalStep: Int, paint: Paint
    ) {
        val horizontalScale = horizontalStep / 100f
        val verticalScale = verticalStep / 100f
        drawHorizontalCurve(pieceCurveSet.top, canvas, startPoint, horizontalScale, verticalScale, paint)
        drawVerticalCurve(pieceCurveSet.left, canvas, startPoint, horizontalScale, verticalScale, paint)
        drawHorizontalCurve(
            pieceCurveSet.bottom,
            canvas,
            Point(startPoint.x, startPoint.y + verticalStep),
            horizontalScale,
            verticalScale,
            paint
        )
        drawVerticalCurve(
            pieceCurveSet.right,
            canvas,
            Point(startPoint.x + horizontalStep, startPoint.y),
            horizontalScale,
            verticalScale,
            paint
        )
    }

    fun drawHorizontalCurve(
        curve: HorizontalCurve, canvas: Canvas, startPoint: Point,
        horizontalScale: Float, verticalScale: Float, paint: Paint
    ) {
        if (curve == HorizontalCurve.NONE) {
            return
        }
        val sign = if (curve == HorizontalCurve.TAB) 1 else -1
        val path = Path()
        path.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
        var i = 0
        while (i + 2 < horizontalTabCoords.size) {
            path.cubicTo(
                startPoint.x + horizontalTabCoords[i].x * horizontalScale,
                startPoint.y + sign.toFloat() * horizontalTabCoords[i].y.toFloat() * verticalScale,
                startPoint.x + horizontalTabCoords[i + 1].x * horizontalScale,
                startPoint.y + sign.toFloat() * horizontalTabCoords[i + 1].y.toFloat() * verticalScale,
                startPoint.x + horizontalTabCoords[i + 2].x * horizontalScale,
                startPoint.y + sign.toFloat() * horizontalTabCoords[i + 2].y.toFloat() * verticalScale
            )
            i += 2
        }
        canvas.drawPath(path, paint)
    }

    fun drawVerticalCurve(
        curve: VerticalCurve, canvas: Canvas, startPoint: Point,
        horizontalScale: Float, verticalScale: Float, paint: Paint
    ) {
        if (curve === VerticalCurve.NONE) {
            return
        }
        val sign = if (curve === VerticalCurve.TAB) 1 else -1
        val path = Path()
        path.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
        var i = 0
        while (i + 2 < verticalTabCoords.size) {
            path.cubicTo(
                startPoint.x + sign.toFloat() * verticalTabCoords[i].x.toFloat() * horizontalScale,
                startPoint.y + verticalTabCoords[i].y * verticalScale,
                startPoint.x + sign.toFloat() * verticalTabCoords[i + 1].x.toFloat() * horizontalScale,
                startPoint.y + verticalTabCoords[i + 1].y * verticalScale,
                startPoint.x + sign.toFloat() * verticalTabCoords[i + 2].x.toFloat() * horizontalScale,
                startPoint.y + verticalTabCoords[i + 2].y * verticalScale
            )
            i += 2
        }
        canvas.drawPath(path, paint)
    }
}

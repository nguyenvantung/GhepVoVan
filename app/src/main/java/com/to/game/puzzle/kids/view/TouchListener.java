package com.to.game.puzzle.kids.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.to.game.puzzle.kids.model.PuzzlePiece;
import com.to.game.puzzle.kids.ui.fragment.PaintingFragment;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

public class TouchListener implements View.OnTouchListener {
    private float xDelta;
    private float yDelta;
    private PaintingFragment fragment;
    private PuzzlePiece piece;

    public TouchListener() {
    }

    public void setFragment(PaintingFragment fragment) {
        this.fragment = fragment;
    }

    public void setPiece(PuzzlePiece piece) {
        this.piece = piece;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();
        final double tolerance = sqrt(pow(view.getWidth(), 2) + pow(view.getHeight(), 2)) / 10;
        if (!piece.canMove) {
            return true;
        }

        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xDelta = x - lParams.leftMargin;
                yDelta = y - lParams.topMargin;
                piece.bringToFront();
                break;
            case MotionEvent.ACTION_MOVE:
                lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                lParams.leftMargin = (int) (x - xDelta);
                lParams.topMargin = (int) (y - yDelta);
                lParams.bottomMargin = -250;
                lParams.rightMargin = -250;
                view.setLayoutParams(lParams);
                int xDiff = abs(piece.xCoord - lParams.leftMargin);
                int yDiff = abs(piece.yCoord - lParams.topMargin);
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    piece.canMove = false;
                    lParams.leftMargin = piece.xCoord;
                    lParams.topMargin = piece.yCoord;
                    view.setLayoutParams(lParams);
                    fragment.handleShowView(view.getId());
                    sendViewToBack(piece);
                }
                break;
            case MotionEvent.ACTION_UP:
               /* int xDiff = abs(piece.xCoord - lParams.leftMargin);
                int yDiff = abs(piece.yCoord - lParams.topMargin);
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    lParams.leftMargin = piece.xCoord;
                    lParams.topMargin = piece.yCoord;
                    piece.setLayoutParams(lParams);
                    piece.canMove = false;
                    sendViewToBack(piece);
                    //activity.checkGameOver();
                }*/
                break;
        }

        return true;
    }

    public void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup)child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }
}

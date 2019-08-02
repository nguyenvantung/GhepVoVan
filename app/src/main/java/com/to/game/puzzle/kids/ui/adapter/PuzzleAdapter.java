package com.to.game.puzzle.kids.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.to.game.puzzle.kids.R;
import com.to.game.puzzle.kids.model.Pieces;
import com.to.game.puzzle.kids.ui.activity.PuzzleActivity;
import com.to.game.puzzle.kids.util.AnimationUtils;

import java.util.ArrayList;
import java.util.List;


public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.DateViewHolder> {

    int mPreviousPosition=0;
    Context context;
    List<Pieces> piecesModelList = new ArrayList<Pieces>();

    public PuzzleAdapter(Context mContext, List<Pieces> piecesModelList) {
        this.context = mContext;
        this.piecesModelList = piecesModelList;
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puzzle_item,
                parent, false);
        return new DateViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder( DateViewHolder holder, int position) {
        holder.imageView.setImageBitmap(piecesModelList.get(position).getOriginalResource());
        holder.imageView.setTag("" + piecesModelList.get(position).getpX() + "," + piecesModelList.get(position).getpY());
        holder.imageView.setOnLongClickListener(new PuzzleActivity.MyClickListener());
        animationmethod(holder,position);
    }

    @Override
    public int getItemCount() {
        return piecesModelList.size();
    }


    public class DateViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;

        public DateViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    void animationmethod(DateViewHolder holder, int position)
    {
        if (position > mPreviousPosition) {
            AnimationUtils.animate(holder,true);
        } else {
            AnimationUtils.animate(holder, false);
        }
        mPreviousPosition = position;
    }
}

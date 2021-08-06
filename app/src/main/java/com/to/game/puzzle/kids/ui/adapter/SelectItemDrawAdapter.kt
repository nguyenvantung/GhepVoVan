package com.to.game.puzzle.kids.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.`interface`.OnClickItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.choiseview_item.*
import java.io.IOException
import java.io.InputStream

class SelectItemDrawAdapter(private val listData: Array<String>, val context: Context, val item: String)
    : RecyclerView.Adapter<SelectItemDrawAdapter.SelectViewHolder>() {

    private var onClickItem: OnClickItem? = null

    fun setOnClickItem(onClick: OnClickItem?) {
        onClickItem = onClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SelectViewHolder {
        return SelectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.choiseview_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: SelectViewHolder, position: Int) {
        try {
            val filePath: String = item + "/" + listData[position]
            val inputStream: InputStream = context.getAssets().open(filePath)
            val drawable = Drawable.createFromStream(inputStream, null)
            viewHolder.setData(drawable, filePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = listData.size

    inner class SelectViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var fileImage: String? = null

        fun setData(drawable: Drawable?, file: String) {
            fileImage = file
            imgItem.setImageDrawable(drawable)
            containerView.setOnClickListener {
                onClickItem?.let {
                    it.onClick(fileImage?: "")
                }
            }
        }

    }
}
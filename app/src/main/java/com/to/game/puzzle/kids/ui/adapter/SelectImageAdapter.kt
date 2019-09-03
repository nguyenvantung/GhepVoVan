package com.to.game.puzzle.kids.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.to.game.puzzle.kids.R
import android.graphics.drawable.Drawable
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.extensions.LayoutContainer
import java.io.IOException
import java.io.InputStream
import kotlinx.android.synthetic.main.layout_item_image.*


class SelectImageAdapter(path: String, listImage: MutableList<String>, onCLickItemInterface: OnCLickItemInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData = listImage
    val pathImage = path
    private val onCLickItemInterface : OnCLickItemInterface = onCLickItemInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is ImageViewHolder){
           holder.binData(listData[position])
       }
    }

    inner class ImageViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun binData(imageUrl: String){
            ivImageItem.setImageDrawable(UiUtil.getDrawable(containerView.context,"$pathImage/$imageUrl"))
            ivImageItem.setOnClickListener {
                onCLickItemInterface?.onClickItem("$pathImage/$imageUrl")
            }
        }
    }
}
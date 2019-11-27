package com.to.game.puzzle.kids.ui.fragment

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.model.AnimalPiece
import com.to.game.puzzle.kids.ui.activity.BaseFragment
import com.to.game.puzzle.kids.util.FragmentUtil
import com.to.game.puzzle.kids.view.TouchListener
import kotlinx.android.synthetic.main.painting_fragment.*
import java.io.IOException
import java.util.*


@Suppress("DEPRECATION")
class PaintingFragment : BaseFragment(){
    override fun getResourceLayoutId(): Int {
        return R.layout.painting_fragment
    }

    /* private var listImageMove: MutableList<String> = arrayListOf()
     private var listImagePie: MutableList<String> = arrayListOf()

     private var listImageMoveSelect: MutableList<String> = arrayListOf()
     private var listImagePieSelect: MutableList<String> = arrayListOf()
     private var numberItem: Int = 0
     private var number: Int = 0

     private var mediaPlayer: MediaPlayer? = null

     companion object{
         fun newInstance(): PaintingFragment{
             return PaintingFragment()
         }
     }

     override fun getResourceLayoutId(): Int {
         return R.layout.painting_fragment
     }

     override fun initView(savedInstanceState: Bundle?) {
         super.initView(savedInstanceState)

         handlePositionViewMove()

         listImageMove = getFileItem("animal")
         listImagePie = getFileItem("animal1")
         Handler().postDelayed({
             handleGetImageAssets()
             handleSetupImageMove()
         }, 500)

     }

     private fun getFileItem(path: String): MutableList<String> {
         var list: Array<String>? = null
         try {
             list = activity!!.assets.list(path)
         } catch (e: IOException) {
             e.printStackTrace()
         }
         return list!!.toMutableList()
     }

     private fun handleGetImage(){
         if (number == 4){
             return
         }
         numberItem = Random().nextInt(listImagePie.size - 1) + 0
         val imageUrl = listImageMove[numberItem]
         val imageUrlPie = listImagePie[numberItem]
         if (listImageMoveSelect.size > 0 && listImagePieSelect.size > 0){
             if (!listImageMoveSelect.contains(imageUrl) && !listImagePieSelect.contains(imageUrlPie)){
                 listImageMoveSelect.add(imageUrl)
                 listImagePieSelect.add(imageUrlPie)
                 number++
             }
             handleGetImage()
         }else{
             listImageMoveSelect.add(imageUrl)
             listImagePieSelect.add(imageUrlPie)
             number++
             handleGetImage()
         }

     }

     private fun handleGetImageAssets(){
         handleGetImage()
         try {
             val urlMove1 = activity!!.assets.open("animal/" + listImageMoveSelect[0])
             val urlPie1 = activity!!.assets.open("animal1/"+listImagePieSelect[0])
             val urlMove2 = activity!!.assets.open("animal/" +listImageMoveSelect[1])
             val urlPie2 = activity!!.assets.open("animal1/" +listImagePieSelect[1])
             val urlMove3 = activity!!.assets.open("animal/" +listImageMoveSelect[2])
             val urlPie3 = activity!!.assets.open("animal1/" +listImagePieSelect[2])
             val urlMove4 = activity!!.assets.open("animal/" +listImageMoveSelect[3])
             val urlPie4 = activity!!.assets.open("animal1/" +listImagePieSelect[3])

             val bitmapMove1 = BitmapFactory.decodeStream(urlMove1)
             val bitmapPie1 = BitmapFactory.decodeStream(urlPie1)
             val bitmapMove2 = BitmapFactory.decodeStream(urlMove2)
             val bitmapPie2 = BitmapFactory.decodeStream(urlPie2)
             val bitmapMove3 = BitmapFactory.decodeStream(urlMove3)
             val bitmapPie3 = BitmapFactory.decodeStream(urlPie3)
             val bitmapMove4 = BitmapFactory.decodeStream(urlMove4)
             val bitmapPie4 = BitmapFactory.decodeStream(urlPie4)
             imageMove1.setImageBitmap(bitmapMove1)
             imageMove2.setImageBitmap(bitmapMove2)
             imageMove3.setImageBitmap(bitmapMove3)
             imageMove4.setImageBitmap(bitmapMove4)

             imagePie1.setImageBitmap(bitmapPie1)
             imagePie2.setImageBitmap(bitmapPie2)
             imagePie3.setImageBitmap(bitmapPie3)
             imagePie4.setImageBitmap(bitmapPie4)

         }catch (ex: IOException){
             ex.printStackTrace()
         }
     }

     private fun handleSetupImageMove(){
         val displayMetrics = DisplayMetrics()
         activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
         val height = displayMetrics.heightPixels

         val location = IntArray(2)

         imagePie1.getLocationOnScreen(location)
         var x1 = location[0]
         val puzzlePiece1 = AnimalPiece(activity!!)

         puzzlePiece1.canMove = true
         puzzlePiece1.xCoord = x1
         puzzlePiece1.yCoord = 0
         puzzlePiece1.pieceWidth = 450
         puzzlePiece1.pieceHeight = 450
         val touchListener1 = TouchListener()
         touchListener1.setFragment(this)
         touchListener1.setPiece(puzzlePiece1)
         imageMove1.setOnTouchListener(touchListener1)

         val puzzlePiece2 = AnimalPiece(activity!!)
         imagePie2.getLocationOnScreen(location)
         var x2 = location[0]
         puzzlePiece2.canMove = true
         puzzlePiece2.xCoord = x2
         puzzlePiece2.yCoord = height - (imagePie2.height + 80)
         puzzlePiece2.pieceWidth = 450
         puzzlePiece2.pieceHeight = 450
         val touchListener2 = TouchListener()
         touchListener2.setFragment(this)
         touchListener2.setPiece(puzzlePiece2)
         imageMove2.setOnTouchListener(touchListener2)

         val puzzlePiece3 = AnimalPiece(activity!!)
         imagePie3.getLocationOnScreen(location)
         var x3 = location[0]
         puzzlePiece3.canMove = true
         puzzlePiece3.xCoord = x3
         puzzlePiece3.yCoord = 0
         puzzlePiece3.pieceWidth = 450
         puzzlePiece3.pieceHeight = 450
         val touchListener3 = TouchListener()
         touchListener3.setFragment(this)
         touchListener3.setPiece(puzzlePiece3)
         imageMove3.setOnTouchListener(touchListener3)

         var x4 = location[0]
         val puzzlePiece4 = AnimalPiece(activity!!)
         puzzlePiece4.canMove = true
         puzzlePiece4.xCoord = x4
         puzzlePiece4.yCoord = height - (imagePie4.height + 80)
         puzzlePiece4.pieceWidth = 450
         puzzlePiece4.pieceHeight = 450
         val touchListener4 = TouchListener()
         touchListener4.setFragment(this)
         touchListener4.setPiece(puzzlePiece4)
         imageMove4.setOnTouchListener(touchListener4)

     }

     fun handleShowView(id: Int){
         val showAnimation = AnimationUtils.loadAnimation(activity, R.anim.show_view)
         when(id){
             R.id.imageMove1 -> {
                 mudicDone()
                 imageMove2.visibility = View.VISIBLE
                 imageMove2.startAnimation(showAnimation)
                 animationPie1.visibility = View.VISIBLE
             }
             R.id.imageMove2 -> {
                 mudicDone()
                 imageMove3.visibility = View.VISIBLE
                 imageMove3.startAnimation(showAnimation)
                 animationPie2.visibility = View.VISIBLE
             }
             R.id.imageMove3 -> {
                 mudicDone()
                 imageMove4.visibility = View.VISIBLE
                 imageMove4.startAnimation(showAnimation)
                 animationPie3.visibility = View.VISIBLE
             }
             R.id.imageMove4 -> {
                 mudicEnd()
                 animationPie4.visibility = View.VISIBLE
                 animationEnd.visibility = View.VISIBLE
                 Handler().postDelayed({
                     mediaPlayer!!.stop()
                     FragmentUtil.replaceFragment(activity!!, newInstance())}, 2000)
                 number = 0
                 listImageMoveSelect.clear()
                 listImagePieSelect.clear()
             }
         }

     }

     private fun handlePositionViewMove(){
         val displayMetrics = DisplayMetrics()
         activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
         val height = displayMetrics.heightPixels
         val width = displayMetrics.widthPixels
         val layoutParams1 = RelativeLayout.LayoutParams(imageMove1.layoutParams)
         val layoutParams2 = RelativeLayout.LayoutParams(imageMove2.layoutParams)
         val layoutParams3 = RelativeLayout.LayoutParams(imageMove3.layoutParams)
         val layoutParams4 = RelativeLayout.LayoutParams(imageMove4.layoutParams)
         layoutParams1.leftMargin = width/2 - 200
         layoutParams1.topMargin = height/2 - 250
         imageMove1.layoutParams = layoutParams1

         layoutParams2.leftMargin = width/2 - 200
         layoutParams2.topMargin = height/2 - 250
         imageMove2.layoutParams = layoutParams2

         layoutParams3.leftMargin = width/2 - 200
         layoutParams3.topMargin = height/2 - 250
         imageMove3.layoutParams = layoutParams3

         layoutParams4.leftMargin = width/2 - 200
         layoutParams4.topMargin = height/2 - 250
         imageMove4.layoutParams = layoutParams4
     }

     private fun mudicDone() {
         val mediaPlayer = MediaPlayer.create(context, R.raw.sf_0)
         mediaPlayer.start()
     }
     private fun mudicEnd() {
         mediaPlayer = MediaPlayer.create(context, R.raw.phaohoa)
         mediaPlayer!!.start()
     }*/
}
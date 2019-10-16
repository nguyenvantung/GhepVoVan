package com.to.game.puzzle.kids.ui.activity

import android.annotation.TargetApi
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.to.game.puzzle.kids.R
import com.to.game.puzzle.kids.constants.AppConstants
import com.to.game.puzzle.kids.model.Pieces
import com.to.game.puzzle.kids.model.Puzzle
import com.to.game.puzzle.kids.model.PuzzlePiece
import com.to.game.puzzle.kids.ui.adapter.PuzzleAdapter
import com.to.game.puzzle.kids.util.UiUtil
import kotlinx.android.synthetic.main.puzzle_activity.*
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class PuzzleActivity : FragmentActivity() {
    private var piecesModelListMain: MutableList<Pieces> = ArrayList()
    private var piecesModelHashMap = HashMap<String, Pieces>()
    private var countGrid = 0
    private var puzzlePiecesList = ArrayList<PuzzlePiece>()
    private var puzzleListAdapter: PuzzleAdapter? = null
    internal var puzzle = Puzzle()
    private var drawable: Drawable? = null

    private var widthCheck = true
    private var widthFinal: Int = 0
    private var heightFinal: Int = 0
    private var mediaPlayer: MediaPlayer? = null

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puzzle_activity)

        scrollView.setOnDragListener(MyDragListener(null))
        relativeLayout.setOnDragListener(MyDragListener(null))
        listView2.setOnDragListener(MyDragListener(null))
        listView2!!.layoutManager = LinearLayoutManager(applicationContext)
        puzzle = Puzzle()
        puzzlePiecesList.clear()

        drawable = UiUtil.getDrawable(this, intent.getStringExtra(AppConstants.KEY_IMAGE_PUZZLE))
        val obs = scrollView.viewTreeObserver
        obs.addOnGlobalLayoutListener {
            if (widthCheck) {
                widthFinal = scrollView.width
                heightFinal = scrollView.height
                puzzlePiecesList = puzzle.createPuzzlePieces(this, widthFinal, heightFinal, frameImage,
                    drawable!!,"/puzzles/",AppConstants.horizontalResolution, AppConstants.verticalResolution)
                getAdapter()
                widthCheck = false
            }
        }

        setPuzzleListAdapter()
    }

    private fun getAdapter() {
        var params: RelativeLayout.LayoutParams
        val horizontal = AppConstants.horizontalResolution - 1
        val vertical = AppConstants.verticalResolution - 1
        for (i in 0..horizontal) {
            for (j in 0..vertical) {
                val piece = puzzlePiecesList[countGrid]
                params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )

                val dimX = piece.anchorPoint!!.x - piece.centerPoint!!.x
                val dimY = piece.anchorPoint!!.y - piece.centerPoint!!.y

                params.setMargins(dimX, dimY, 0, 0)
                val button2 = ImageView(this)
                button2.id = generateViewId()
                button2.tag = "$i,$j"

                button2.setImageResource(R.drawable.ic_1)

                button2.setOnDragListener(MyDragListener(button2))
                button2.layoutParams = params
                relativeLayout.addView(button2)

                var piecesModel: Pieces? = Pieces()
                piecesModel!!.setpX(i)
                piecesModel.setpY(j)
                piecesModel.setPosition(countGrid)
                piecesModel.setOriginalResource(puzzlePiecesList[countGrid].image!!)
                piecesModelListMain.add(piecesModel)
                piecesModelListMain.shuffle()
                piecesModelHashMap["$i,$j"] = piecesModel
                piecesModel = null
                countGrid++

            }
        }
    }


    private fun generateViewId(): Int {
        val sNextGeneratedId = AtomicInteger(1)
        while (true) {
            val result = sNextGeneratedId.get()
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            var newValue = result + 1
            if (newValue > 0x00FFFFFF) newValue = 1 // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result
            }
        }
    }

    fun setPuzzleListAdapter() {
        if (puzzleListAdapter != null)
            puzzleListAdapter = null

        puzzleListAdapter = PuzzleAdapter(this, piecesModelListMain)
        listView2!!.setHasFixedSize(true)
        listView2!!.adapter = puzzleListAdapter

        puzzleListAdapter!!.notifyDataSetChanged()
    }

    class MyClickListener : View.OnLongClickListener {

        // called when the item is long-clicked
        override fun onLongClick(view: View): Boolean {
            // create it from the object's tag
            val item = ClipData.Item(view.tag as CharSequence)

            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(view.tag.toString(), mimeTypes, item)
            val shadowBuilder = View.DragShadowBuilder(view)
            //UiUtil.playTouch(PuzzleActivity())
            view.startDrag(
                data, //data to be dragged
                shadowBuilder, //drag shadow
                view, //local data about the drag and drop operation
                0   //no needed flags
            )

            view.visibility = View.INVISIBLE
            return true
        }
    }

    private fun mudicEnd() {
        mediaPlayer = MediaPlayer.create(this, R.raw.phaohoa)
        mediaPlayer!!.start()
    }

    inner class MyDragListener(private val imageView: ImageView?) : View.OnDragListener {


        override fun onDrag(v: View, event: DragEvent): Boolean {

            // Handles each of the expected events
            when (event.action) {

                //signal for the start of a drag and drop operation.
                DragEvent.ACTION_DRAG_STARTED -> {
                }

                //the drag point has entered the bounding box of the View
                DragEvent.ACTION_DRAG_ENTERED -> {
                }

                //the user has moved the drag shadow outside the bounding box of the View
                DragEvent.ACTION_DRAG_EXITED -> {
                }

                //drag shadow has been released,the drag point is within the bounding box of the View
                DragEvent.ACTION_DROP ->
                    //v is the dynamic grid imageView, we accept the drag item
                    //view is listView imageView the dragged item
                    if (v === imageView) {
                        val view = event.localState as View

                        val owner = v.getParent() as ViewGroup
                        if (owner === relativeLayout) {
                            val selectedViewTag = view.tag.toString()

                            var piecesModel = piecesModelHashMap[v.getTag().toString()]
                            val xy = piecesModel!!.getpX().toString() + "," + piecesModel.getpY()

                            if (xy == selectedViewTag) {
                                v.setImageBitmap(piecesModel.getOriginalResource())
                                piecesModelListMain.remove(piecesModel)
                                setPuzzleListAdapter()
                                piecesModel = null
                                if (piecesModelListMain.size == 0) {
                                    animationEndPuzzle.visibility = View.VISIBLE
                                    Handler().postDelayed({
                                        mediaPlayer?.stop()
                                        finish()}, 1000)
                                }
                            } else {
                                piecesModel = null
                                view.visibility = View.VISIBLE
                                //Toast.makeText(applicationContext, "Not the correct Puzzle", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val view1 = event.localState as View
                            view1.visibility = View.VISIBLE
                            //Toast.makeText(applicationContext, "You can't drop the image here", Toast.LENGTH_LONG).show()
                        }
                    } else if (v === scrollView) {
                        val view1 = event.localState as View
                        view1.visibility = View.VISIBLE
                        //Toast.makeText(applicationContext, "You can't drop the image here", Toast.LENGTH_LONG).show()
                    } else if (v === listView2) {
                        val view1 = event.localState as View
                        view1.visibility = View.VISIBLE
                        //Toast.makeText(applicationContext, "You can't drop the image here", Toast.LENGTH_LONG).show()
                    } else {
                        val view = event.localState as View
                        view.visibility = View.VISIBLE
                        //Toast.makeText(applicationContext, "You can't drop the image here", Toast.LENGTH_LONG).show()
                    }
                DragEvent.ACTION_DRAG_ENDED -> {
                }
                else -> {
                }
            }
            return true
        }
    }
}

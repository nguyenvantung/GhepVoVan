package com.to.game.puzzle.kids

import android.app.Application

class PuzzleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object{
        private var context: PuzzleApplication? = null
        public fun getApplication(): PuzzleApplication {
            return context!!
        }
    }
}
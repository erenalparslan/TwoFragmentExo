package com.erenalparslan.twofragments

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.exoplayer2.MediaItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     //   Navigation.findNavController(this,R.id.fragmentContainerView).currentDestination

        fab_button.setOnClickListener{
            Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.transitionFragment)
            if(Playlist.mediaItems.size!=1)
                Playlist.mediaItems.removeAt(Playlist.mediaItems.size-1)
        }



    }

    override fun onResume() {
        super.onResume()
        //println("resume Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        //println("destroy Activity")
    }

    override fun onPause() {
        super.onPause()
        //println("pause Activity")
    }

    override fun onStop() {
        super.onStop()
        //println("stop Activity")
    }

    override fun onRestart() {
        super.onRestart()
       // println("restart Activity")
    }

    override fun onStart() {
        super.onStart()


    }
}
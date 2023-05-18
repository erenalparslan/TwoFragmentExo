package com.erenalparslan.twofragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_two_video.*


class TwoVideoFragment : Fragment() {
    lateinit var player: ExoPlayer
    lateinit var player1: ExoPlayer
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    inner class PlayerEventListener : Player.Listener {

        override fun onPlaybackStateChanged(state: Int) {
            super.onPlaybackStateChanged(state)


        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            println("READY--->" + error)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            println("isPlaying--->" + isPlaying)
        }

        override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
            super.onSeekForwardIncrementChanged(seekForwardIncrementMs)
            println("SEEK--->" + seekForwardIncrementMs.toInt())
        }

        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            /*println("PLAYER--->" + player)
            println("EVENTS--->" + events)*/
        }

        override fun onTracksChanged(tracks: Tracks) {
            super.onTracksChanged(tracks)
            println("TRACKS--->" + tracks.toString())


        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player = ExoPlayer.Builder(requireContext()).build()
        player1 = ExoPlayer.Builder(requireContext()).build()

        if (savedInstanceState != null) {
            currentWindow = savedInstanceState.getInt("current_window")
            playbackPosition = savedInstanceState.getLong("playback_position")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two_video, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(" TWO Fragment CREATE")
        exoPlay1.player = player
        exoPlay2.player=player1
        playerSetter(player,Playlist.mediaItems)
        playerSetter(player1,Playlist.mediaItems)
        player1.volume=0f

        println(Playlist.mediaItems.size)
        if(Playlist.mediaItems.size==1){
           // onDestroy()
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        println(" TWO Fragment destroy")
        player.release()
        player1.release()
    }

    fun playerSetter(player: ExoPlayer, mediaItem: List<MediaItem>) {
        player.setMediaItems(mediaItem, currentWindow, playbackPosition)
        player.prepare()
        player.play()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Player state'ini kaydet
        outState.putInt("current_window", player.currentWindowIndex)
        outState.putLong("playback_position", player.currentPosition)
    }
}
package com.erenalparslan.twofragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import kotlinx.android.synthetic.main.fragment_one_video.*


class OneVideoFragment : Fragment() {

    lateinit var player: ExoPlayer
    private var nextIndex = 0
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0
    var playlist=Playlist.mediaItems

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
          //  println("PLAYER--->" + player)
            //println("EVENTS--->" + events)
        }

        override fun onTracksChanged(tracks: Tracks) {
            super.onTracksChanged(tracks)
          //  println("TRACKS--->" + tracks.toString())


        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = ExoPlayer.Builder(requireContext()).build()
        val playerEventListener = PlayerEventListener()
        player.addListener(playerEventListener)

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
        return inflater.inflate(R.layout.fragment_one_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(" ONE Fragment CREATE")
        exoPlay.player=player
        playerSetter(player, playlist)





    }

    override fun onDestroy() {
        super.onDestroy()
        println("ONE FRAGMENT DESTROY")
        player.release()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Player state'ini kaydet
        outState.putInt("current_window", player.currentWindowIndex)
        outState.putLong("playback_position", player.currentPosition)
    }


    fun playerSetter(player: ExoPlayer, mediaItem: List<MediaItem>) {
        player.setMediaItems(mediaItem,currentWindow,playbackPosition)
        player.prepare()
        player.play()
    }


}
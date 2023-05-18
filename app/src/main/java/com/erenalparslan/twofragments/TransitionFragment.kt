package com.erenalparslan.twofragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class TransitionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var playlist=Playlist.mediaItems


        // TODO: destroy bug will fixed 

        if(playlist.size==1){
            findNavController().navigate(TransitionFragmentDirections.actionTransitionFragmentToOneVideoFragment())
        }
        else if(playlist.size>1){

            findNavController().navigate(TransitionFragmentDirections.actionTransitionFragmentToTwoVideoFragment())
        }
        else  {

        }



    }



}
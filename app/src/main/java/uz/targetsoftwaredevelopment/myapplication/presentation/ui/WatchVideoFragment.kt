package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.media.MediaPlayer.create
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentWatchVideoBinding

class WatchVideoFragment : Fragment() {

    private lateinit var binding:FragmentWatchVideoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchVideoBinding.inflate(inflater,container,false)

//        val mediaController = MediaController(requireContext())
//        mediaController.setAnchorView(binding.videoView)

        val player = ExoPlayer.Builder(requireContext()).build()

        val onlineUri:Uri = Uri.parse("https://www.videezy.com/after-effects-templates/7872-cutting-edge-parallax-4k-opener-after-effects-template")

        val mediaItem:MediaItem = MediaItem.fromUri(onlineUri)
        binding.videoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()



//        binding.videoView.setMediaController(mediaController)
//        binding.videoView.setVideoURI(onlineUri)
//        binding.videoView.requestFocus()
//        binding.videoView.start()



        return binding.root
    }



}
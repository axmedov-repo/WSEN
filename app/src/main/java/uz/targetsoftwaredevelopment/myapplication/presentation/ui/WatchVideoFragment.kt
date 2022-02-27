package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.media.MediaPlayer.create
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentWatchVideoBinding

class WatchVideoFragment : Fragment() {

    private lateinit var binding:FragmentWatchVideoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchVideoBinding.inflate(inflater,container,false)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)



        val onlineUri:Uri = Uri.parse("https://media.istockphoto.com/videos/preparing-mix-salad-with-vegetables-at-home-a-part-of-stirring-the-video-id1216828127")
//      val uri: Uri = Uri.parse(Environment.getExternalStorageDirectory().path + "/video.mp4")


        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(onlineUri)
        binding.videoView.requestFocus()
        binding.videoView.start()


        return binding.root
    }



}
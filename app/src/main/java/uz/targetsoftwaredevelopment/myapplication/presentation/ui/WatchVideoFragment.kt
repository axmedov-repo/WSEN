package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentWatchVideoBinding

class WatchVideoFragment : Fragment() {

    private lateinit var binding:FragmentWatchVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchVideoBinding.inflate(inflater,container,false)
        binding.fullscreenVideoView.videoUrl("https://clips.vorwaerts-gmbh.de/VfE_html5.mp4")
            .enableAutoStart()
            .addSeekBackwardButton()
            .addSeekForwardButton()
            .fastForwardSeconds(5)
            .rewindSeconds(5)
            .addOnErrorListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }





        return binding.root
    }



}
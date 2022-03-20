package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentWatchVideoBinding

class WatchVideoFragment : Fragment() {

    private lateinit var binding:FragmentWatchVideoBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout.bottomSheet)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchVideoBinding.inflate(inflater,container,false)

        val player = ExoPlayer.Builder(requireContext()).build()
        val onlineUri:Uri = Uri.parse(" https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        val mediaItem:MediaItem = MediaItem.fromUri(onlineUri)
        binding.videoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()
        return binding.root
    }





}
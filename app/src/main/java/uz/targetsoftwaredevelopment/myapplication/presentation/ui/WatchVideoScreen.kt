package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenWatchVideoBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope


@AndroidEntryPoint
class WatchVideoScreen : Fragment() {

    private val binding by viewBinding(ScreenWatchVideoBinding::bind)
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=binding.scope {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout.bottomSheet)

        val player = ExoPlayer.Builder(requireContext()).build()
        val onlineUri:Uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        val mediaItem:MediaItem = MediaItem.fromUri(onlineUri)
        binding.videoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()

    }
}
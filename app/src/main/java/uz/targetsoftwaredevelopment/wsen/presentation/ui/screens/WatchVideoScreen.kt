package uz.targetsoftwaredevelopment.wsen.presentation.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenWatchVideoBinding
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class WatchVideoScreen : Fragment(R.layout.screen_watch_video) {
    private val binding by viewBinding(ScreenWatchVideoBinding::bind)
    private lateinit var player: ExoPlayer
    private val args: WatchVideoScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        player = ExoPlayer.Builder(requireContext()).build()
        val onlineUri: Uri = Uri.parse(args.videoData.video)
        val mediaItem: MediaItem = MediaItem.fromUri(onlineUri)
        watchVideoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }
}

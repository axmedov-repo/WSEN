package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenEditVideoBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class EditVideoScreen:Fragment(R.layout.screen_edit_video) {
    private val binding by viewBinding(ScreenEditVideoBinding::bind)
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)=binding.scope {
        super.onViewCreated(view, savedInstanceState)

        player = ExoPlayer.Builder(requireContext()).build()
//        val onlineUri:Uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        val onlineUri: Uri = Uri.parse("https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4")
        val mediaItem: MediaItem = MediaItem.fromUri(onlineUri)
        editVideoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()

    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }
}
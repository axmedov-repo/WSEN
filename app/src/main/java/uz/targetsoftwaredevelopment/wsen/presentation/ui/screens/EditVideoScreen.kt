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
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenEditVideoBinding
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class EditVideoScreen : Fragment(R.layout.screen_edit_video) {
    private val binding by viewBinding(ScreenEditVideoBinding::bind)
    private lateinit var player: ExoPlayer
    private val args: EditVideoScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        player = ExoPlayer.Builder(requireContext()).build()
        val onlineUri: Uri =
            Uri.parse(args.videoData.video)
        val mediaItem: MediaItem = MediaItem.fromUri(onlineUri)
        editVideoView.player = player
        player.setMediaItem(mediaItem)
        player.prepare()


        btnEditVideo.setOnClickListener {
            if(editTitleEt.text.isNotEmpty() && editDescriptionEt.text.isNotEmpty() && editLocationEt.text.isNotEmpty()){
                // TODO: maluotlar olinib post qilinadi
            }
        }


    }

    private fun loadData() {
        binding.apply {
            editTitleEt.setText(args.videoData.title)
            editDescriptionEt.setText(args.videoData.desc)
            editLocationEt.setText(args.videoData.location)
        }
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }
}
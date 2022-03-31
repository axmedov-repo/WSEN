package uz.targetsoftwaredevelopment.wsen.presentation.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.EditVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.EditVideoResponse
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenEditVideoBinding
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.EditVideoScreenViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl.EditVideoScreenViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.gone
import uz.targetsoftwaredevelopment.wsen.utils.scope
import uz.targetsoftwaredevelopment.wsen.utils.visible

@AndroidEntryPoint
class EditVideoScreen : Fragment(R.layout.screen_edit_video) {
    private val binding by viewBinding(ScreenEditVideoBinding::bind)
    private lateinit var player: ExoPlayer
    private val viewModel: EditVideoScreenViewModel by viewModels<EditVideoScreenViewModelImpl>()
    private val args: EditVideoScreenArgs by navArgs()

    private var myvideospageListener: (() -> Unit)? = null
    fun setMyVideoPageListener(f: () -> Unit) {
        myvideospageListener = f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        progressBar.animate()
        progressBar.visible()

        loadData()

        btnEditVideo.setOnClickListener {
            if (editTitleEt.text.isNotEmpty() && editDescriptionEt.text.isNotEmpty() && editLocationEt.text.isNotEmpty()) {
                viewModel.editVideo(
                    EditVideoRequest(
                        args.videoData.location,
                        args.videoData.title,
                        args.videoData.desc,
                        args.videoData.category,
                        args.videoData.id
                    )
                )
            }
        }

        viewModel.editVideoLiveData.observe(viewLifecycleOwner, editVideoObserver)
    }

    private val editVideoObserver = Observer<EditVideoResponse> {
        findNavController().popBackStack()
    }

    private fun loadData() {
        binding.apply {
            player = ExoPlayer.Builder(requireContext()).build()
            val onlineUri: Uri =
                Uri.parse(args.videoData.video)
            val mediaItem: MediaItem = MediaItem.fromUri(onlineUri)
            editVideoView.player = player
            player.setMediaItem(mediaItem)
            player.prepare()

            editTitleEt.setText(args.videoData.title)
            editDescriptionEt.setText(args.videoData.desc)
            editLocationEt.setText(args.videoData.location)
        }
        binding.progressBar.clearAnimation()
        binding.progressBar.gone()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }
}
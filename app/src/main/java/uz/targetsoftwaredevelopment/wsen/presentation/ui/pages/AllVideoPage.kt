package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.databinding.PageAllVideoBinding
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenBottomSheetDialogBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.AllVideoRvAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.AllVideoPageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.AllVideoPageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class AllVideoPage:Fragment(R.layout.page_all_video) {
    private val binding by viewBinding(PageAllVideoBinding::bind)
    private val viewModel : AllVideoPageViewModel by viewModels<AllVideoPageViewModelImpl>()
    private lateinit var allVideoRvAdapter : AllVideoRvAdapter

    private var videoClickedListener : ((VideoData) -> Unit)? = null
    fun setVideoClickedListener(f : (VideoData) -> Unit) {
        videoClickedListener = f
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        loadAllVideoData()
        viewModel.getAllVideos()
        viewModel.allVideosLiveData.observe(viewLifecycleOwner , allVideosObserver)
    }

    private fun loadAllVideoData() {
        allVideoRvAdapter =
            AllVideoRvAdapter(requireContext() , object:AllVideoRvAdapter.OnItemClickListener {
                override fun onItemClick(videoData : VideoData) {
                    videoClickedListener?.invoke(videoData)
                }

                override fun onShareClick(videoData : VideoData) {
                    val shareIntent : Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT , videoData.video)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(shareIntent , null))
                }

                override fun onMenuClick(videoData : VideoData) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext())
                    val screenBottomSheetDialogScreen =
                        ScreenBottomSheetDialogBinding.inflate(layoutInflater)
                    bottomSheetDialog.setContentView(screenBottomSheetDialogScreen.root)

                    screenBottomSheetDialogScreen.sendReportCv.setOnClickListener {

                    }
                    bottomSheetDialog.show()
                }

                override fun onClickLike(videoData : VideoData) {
                    TODO("Not yet implemented")
                }

                override fun onClickUnLike(videoData : VideoData) {
                    TODO("Not yet implemented")
                }
            })
        binding.allVideRv.adapter = allVideoRvAdapter
    }

    private val allVideosObserver = Observer<List<VideoData?>?> {
        allVideoRvAdapter.submitList(it)
    }
}
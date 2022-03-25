package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.databinding.PageAllVideoBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenBottomSheetDialogBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.AllVideoRvAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.AllVideoPageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.AllVideoPageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class AllVideoPage : Fragment(R.layout.page_all_video) {
    private val binding by viewBinding(PageAllVideoBinding::bind)
    private val viewModel: AllVideoPageViewModel by viewModels<AllVideoPageViewModelImpl>()
    private lateinit var allVideoRvAdapter: AllVideoRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        loadAllVideoData()
        viewModel.getAllVideos()
        viewModel.allVideosLiveData.observe(viewLifecycleOwner, allVideosObserver)
    }

    private fun loadAllVideoData() {
        allVideoRvAdapter =
            AllVideoRvAdapter(requireContext(), object : AllVideoRvAdapter.OnItemClickListener {
                override fun onItemClick(videoData: VideoData) {
                    findNavController().navigate(R.id.watchVideoScreen)
                }

                override fun onShareClick(videoData: VideoData) {
//                    val shareIntent: Intent = Intent().apply {
//                        action = Intent.ACTION_SEND
//                        putExtra(Intent.EXTRA_STREAM, videoData.title)
//                        type = "video/mp4"
//                    }
//                    startActivity(Intent.createChooser(shareIntent, null))
                    Toast.makeText(requireContext(), "send", Toast.LENGTH_SHORT).show()
                }

                override fun onMenuClick(videoData: VideoData) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext())
                    val screenBottomSheetDialogScreen =
                        ScreenBottomSheetDialogBinding.inflate(layoutInflater)
                        bottomSheetDialog.setContentView(screenBottomSheetDialogScreen.root)
                        screenBottomSheetDialogScreen.sendReportCv.setOnClickListener {
                        Toast.makeText(requireContext(), "send request", Toast.LENGTH_SHORT)
                            .show()
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
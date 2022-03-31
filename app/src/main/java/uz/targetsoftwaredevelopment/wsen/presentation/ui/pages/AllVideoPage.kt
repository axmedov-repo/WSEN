package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.PageAllVideoBinding
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
    lateinit var videoTest:VideoData

    private var videoClickedListener : ((VideoData) -> Unit)? = null
    fun setVideoClickedListener(f : (VideoData) -> Unit) {
        videoClickedListener = f
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        loadAllVideoData()
        viewModel.getAllVideos()
        viewModel.allVideosLiveData.observe(viewLifecycleOwner , allVideosObserver)
        viewModel.changeLikeLiveData.observe(viewLifecycleOwner,changeLikeObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner,errorLiveDataObserver)
    }

    private fun loadAllVideoData() {
        allVideoRvAdapter =
            AllVideoRvAdapter(object:AllVideoRvAdapter.OnItemClickListener {
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
                        bottomSheetDialog.dismiss()
                    }
                    bottomSheetDialog.show()
                }

                override fun onClickLike(videoData : VideoData) {
                    viewModel.changeLikeVideo(videoData)
                    videoTest = videoData
                }
            })
        binding.allVideRv.adapter = allVideoRvAdapter
    }

    private val allVideosObserver = Observer<List<VideoData?>?> {
        allVideoRvAdapter.submitList(it)
    }
    private val changeLikeObserver = Observer<LikeVideResponseData?> {
        Toast.makeText(App.instance , "success" , Toast.LENGTH_SHORT).show()
        viewModel.getAllVideos()
    }
    private val errorLiveDataObserver = Observer<String> {errorMessage->
        if(errorMessage.equals(getString(R.string.some_error))){
            FancyToast.makeText(requireContext(),"Some error",
                FancyToast.LENGTH_LONG,FancyToast.DEFAULT,false).show()
        }
    }


}
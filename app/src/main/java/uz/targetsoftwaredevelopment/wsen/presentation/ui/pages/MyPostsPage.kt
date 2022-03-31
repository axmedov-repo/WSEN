package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.DialogDeleteBinding
import uz.targetsoftwaredevelopment.wsen.databinding.PageMyPostsBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.MyPostsAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.MyPostsPageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.MyPostsPageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.scope
import uz.targetsoftwaredevelopment.wsen.utils.visible

@AndroidEntryPoint
class MyPostsPage : Fragment(R.layout.page_my_posts) {
    private val binding by viewBinding(PageMyPostsBinding::bind)
    private val viewModel: MyPostsPageViewModel by viewModels<MyPostsPageViewModelImpl>()
    private lateinit var myPostsAdapter: MyPostsAdapter
    private val videosList  = ArrayList<VideoData?>()
    private var deletingVideoPos: Int = 0

    private var editMyVideoClickedListener: ((VideoData) -> Unit)? = null
    fun setEditMyVideoClickedListener(f: (VideoData) -> Unit) {
        editMyVideoClickedListener = f
    }

    private var watchMyVideoClickedListener: ((VideoData) -> Unit)? = null
    fun setWatchMyVideoClickedListener(f: (VideoData) -> Unit) {
        watchMyVideoClickedListener = f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        refresh.isRefreshing = true
        viewModel.getAllMyVideos()
        refresh.setOnRefreshListener {
            viewModel.getAllMyVideos()
            refresh.isRefreshing = true
        }

        myPostsAdapter =
            MyPostsAdapter(object : MyPostsAdapter.OnPostItemTouchListener {
                override fun onMenuEdit(videoData: VideoData, pos: Int) {
                    editMyVideoClickedListener?.invoke(videoData)
                }

                override fun onPostClick(videoData: VideoData, pos: Int) {
                    watchMyVideoClickedListener?.invoke(videoData)
                }

                override fun onMenuDelete(videoData: VideoData, pos: Int) {
                    deletingVideoPos = pos
                    val deleteDialog = AlertDialog.Builder(requireContext())
                    val dialogDeleteBinding = DialogDeleteBinding.inflate(layoutInflater)
                    deleteDialog.setView(dialogDeleteBinding.root)

                    val deleteBuilder = deleteDialog.create()
                    deleteBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogDeleteBinding.apply {
                        cancelPostsCv.setOnClickListener {
                            deleteBuilder.cancel()
                        }

                        deletePostCv.setOnClickListener {
                            viewModel.deleteMyVideo(videoData)

                            deleteBuilder.cancel()
                        }
                    }
                    deleteBuilder.show()
                }

//                override fun onMenuWaiting(videoData: VideoData) {
//
//                }
//
//                override fun onMenuFinished(videoData: VideoData) {
//                }
            })
        myVideosRv.adapter = myPostsAdapter

        viewModel.allMyVideosLiveData.observe(viewLifecycleOwner, allMyVideosObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.videoDeletedLiveData.observe(viewLifecycleOwner, videoDeleteObserver)
    }

    private val allMyVideosObserver = Observer<List<VideoData?>> {
        if (it.isEmpty()) {
            binding.havePostTv.visible()
        } else {
            videosList.clear()
            videosList.addAll(it)
            myPostsAdapter.submitList(videosList)
        }
        binding.refresh.isRefreshing = false
    }

    private val errorObserver = Observer<String> { errorMessage ->
        if (errorMessage.equals(getString(R.string.internet_disconnected))) {
        } else {
            FancyToast.makeText(
                requireContext(),
                errorMessage,
                FancyToast.LENGTH_LONG,
                FancyToast.WARNING,
                false
            ).show()
        }
    }

    private val videoDeleteObserver = Observer<Unit> {
        viewModel.getAllMyVideos()
        FancyToast.makeText(
            requireContext(),
            getString(R.string.succed_delete_post),
            FancyToast.LENGTH_LONG,
            FancyToast.CONFUSING,
            R.drawable.delete_btn,
            false
        )
    }
}

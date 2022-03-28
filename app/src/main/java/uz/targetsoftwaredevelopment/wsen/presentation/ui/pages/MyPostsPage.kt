package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

@AndroidEntryPoint
class MyPostsPage : Fragment(R.layout.page_my_posts) {
    private val binding by viewBinding(PageMyPostsBinding::bind)
    private val viewModel: MyPostsPageViewModel by viewModels<MyPostsPageViewModelImpl>()
    private lateinit var myPostsAdapter: MyPostsAdapter

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
        viewModel.getAllMyVideos()

        myPostsAdapter =
            MyPostsAdapter(requireContext(), object : MyPostsAdapter.OnPostItemTouchListener {
                override fun onMenuEdit(videoData: VideoData) {
                    editMyVideoClickedListener?.invoke(videoData)
                }

                override fun onPostClick(videoData: VideoData) {
                    watchMyVideoClickedListener?.invoke(videoData)
                }

                override fun onMenuDelete(videoData: VideoData) {
                    val deleteDialog = AlertDialog.Builder(requireContext())
                    val dialogDeleteBinding = DialogDeleteBinding.inflate(layoutInflater)
                    deleteDialog.setView(dialogDeleteBinding.root)

                    val deleteBuilder = deleteDialog.create()
                    deleteBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogDeleteBinding.apply {
                        cancelPostsCv.setOnClickListener {
                            Toast.makeText(requireContext(), getString(R.string.cancel), Toast.LENGTH_SHORT).show()
                            deleteBuilder.cancel()
                        }

                        deletePostCv.setOnClickListener {
                            // bu yerga postni ochirish kodi yoziladi
                            Toast.makeText(requireContext(), getString(R.string.delete_posts), Toast.LENGTH_SHORT)
                                .show()
                            deleteBuilder.cancel()
                        }
                    }
                    deleteBuilder.show()
                }

                override fun onMenuWaiting(videoData: VideoData) {
                    TODO("Not yet implemented")
                }

                override fun onMenuFinished(videoData: VideoData) {
                    TODO("Not yet implemented")
                }
            })
        myVideosRv.adapter = myPostsAdapter

        viewModel.allMyVideosLiveData.observe(viewLifecycleOwner, allMyVideosObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val allMyVideosObserver = Observer<List<VideoData?>> {
        myPostsAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> { errorMessage ->
        if (errorMessage.equals(getString(R.string.internet_disconnected))) {}
        else {
            FancyToast.makeText(requireContext(),errorMessage,FancyToast.LENGTH_LONG, FancyToast.WARNING,true).show()
        }
    }
}
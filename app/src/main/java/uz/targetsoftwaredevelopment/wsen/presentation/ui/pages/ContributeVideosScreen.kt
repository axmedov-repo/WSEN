package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenContributeVideosBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.ContributeVideosAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.WishPageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.WishPageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class ContributeVideosScreen : Fragment(R.layout.screen_contribute_videos) {
    private val binding by viewBinding(ScreenContributeVideosBinding::bind)
    private lateinit var contributeVideosAdapter: ContributeVideosAdapter
    private val viewModel: WishPageViewModel by viewModels<WishPageViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavouriteVideos()

        contributeVideosAdapter =
            ContributeVideosAdapter(requireContext(), object : ContributeVideosAdapter.OnWishItemTouchListener {
                override fun onWishClick(videoData: VideoData) {
                    viewModel.changeLike(videoData)
                }

                override fun onPostClick(videoData: VideoData) {
                    findNavController().navigate(
                        ContributeVideosScreenDirections.actionFavouriteVideosScreenToWatchVideoScreen(
                            videoData
                        )
                    )
                }
            })

        favouriteToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.favouriteVideosLiveData.observe(viewLifecycleOwner, favouriteVideosObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val favouriteVideosObserver = Observer<List<VideoData?>?> {
        contributeVideosAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> { errorMessage ->
        if (errorMessage.equals(getString(R.string.internet_disconnected))) {}
        else {
            FancyToast.makeText(requireContext(),errorMessage,FancyToast.LENGTH_LONG,FancyToast.ERROR,true)
        }
    }
}

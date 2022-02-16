package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.adapters.SliderAdapter
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentVideosItemBinding


class VideosItemFragment : Fragment() {

    private lateinit var binding:FragmentVideosItemBinding
    private lateinit var sliderAdapter: SliderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosItemBinding.inflate(inflater,container,false)

        setData()
        loadDataCarouseRv()
        onClickListener()

        return binding.root
    }

    private fun setData() {
        val bundle = Bundle(arguments)
        val title = bundle.getString("Title")
        binding.toolbarVideos.title = title
    }

    private fun onClickListener() {
       binding.toolbarVideos.setNavigationOnClickListener {
           findNavController().popBackStack(R.id.homeScreen,false)
       }
    }

    private fun loadDataCarouseRv() {
        sliderAdapter = SliderAdapter(1)
        binding.carouselRv.apply {
            adapter = sliderAdapter
            set3DItem(false)
            setAlpha(false)
            setInfinite(true)
            setFlat(false)
            setIsScrollingEnabled(true)
            setIntervalRatio(0.65f)
        }

    }

}

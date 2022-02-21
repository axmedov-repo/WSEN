package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.adapters.SliderAdapter
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentVideosItemBinding


class VideosItemFragment : Fragment() {

    private lateinit var binding:FragmentVideosItemBinding
    private lateinit var sliderAdapter: SliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosItemBinding.inflate(inflater,container,false)

        sliderAdapter = SliderAdapter(1,requireContext())
//        val decoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
//        binding.carouselRv.addItemDecoration(decoration)
        binding.carouselRv.adapter = sliderAdapter

//        binding.carouselRv.apply {
//            set3DItem(true)
//            setInfinite(false)
//            setFlat(false)
//            setAlpha(false)
//            setIsScrollingEnabled(true)
//            setIntervalRatio(0.65f)
//            setScrollingTouchSlop(1)
//        }

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

    }

}

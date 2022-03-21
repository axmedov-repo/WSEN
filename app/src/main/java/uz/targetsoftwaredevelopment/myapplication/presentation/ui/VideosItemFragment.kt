package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentVideosItemBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.SliderAdapter

@AndroidEntryPoint
class VideosItemFragment : Fragment() {

    private lateinit var binding:FragmentVideosItemBinding
    private lateinit var sliderAdapter: SliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosItemBinding.inflate(inflater,container,false)

        loadDataCarouseRv()
        setTabs()
        return binding.root
    }


    private fun setTabs() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 10, 0)
            tab.requestLayout()
        }
    }


    private fun loadDataCarouseRv() {
        sliderAdapter = SliderAdapter(1,requireContext(),object :SliderAdapter.OnItemClickListener{
            override fun onItemClick(item: Int) {
                findNavController().navigate(R.id.watchVideoFragment)
            }

            override fun onShareClick(item: Int) {
                Toast.makeText(requireContext(), "send", Toast.LENGTH_SHORT).show()
            }
        })

        binding.carouselRv.apply {
            adapter = sliderAdapter
        }

    }

}

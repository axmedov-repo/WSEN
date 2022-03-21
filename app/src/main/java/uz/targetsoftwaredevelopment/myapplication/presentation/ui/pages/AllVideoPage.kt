package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.PageAllVideoBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.SliderAdapter

@AndroidEntryPoint
class AllVideoPage : Fragment(R.layout.page_all_video) {

    private val binding by viewBinding(PageAllVideoBinding::bind)
    private lateinit var sliderAdapter: SliderAdapter

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataCarouseRv()
    }


    private fun loadDataCarouseRv() {
        sliderAdapter = SliderAdapter(1,requireContext(),object :SliderAdapter.OnItemClickListener{
            override fun onItemClick(item: Int) {
                findNavController().navigate(R.id.watchVideoFragment)
            }

            override fun onShareClick(item: Int) {
                Toast.makeText(requireContext(), "send", Toast.LENGTH_SHORT).show()
            }

            override fun onMenuClick(item : Int) {
                Toast.makeText(requireContext(), "spam", Toast.LENGTH_SHORT).show()
            }
        })

        binding.carouselRv.apply {
            adapter = sliderAdapter
        }

    }

}

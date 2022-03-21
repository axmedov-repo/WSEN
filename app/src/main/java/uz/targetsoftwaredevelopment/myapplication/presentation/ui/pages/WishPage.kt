package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentWishBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.WishRvAdapter

@AndroidEntryPoint
class WishPage : Fragment() {

    private lateinit var binding:FragmentWishBinding
    private lateinit var wishRvAdapter: WishRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishBinding.inflate(inflater,container,false)

        wishRvAdapter = WishRvAdapter()
        val itemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        binding.myVideosRv.addItemDecoration(itemDecoration)
        binding.myVideosRv.adapter = wishRvAdapter
        return binding.root

    }


}
package uz.targetsoftwaredevelopment.myapplication.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentItemViewPagerBinding

class ItemViewPagerFragment : Fragment() {

    private lateinit var binding:FragmentItemViewPagerBinding
    var select = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemViewPagerBinding.inflate(inflater,container,false)

        return binding.root
    }


}
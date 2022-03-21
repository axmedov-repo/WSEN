package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.PageWishBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.MyWishAdapter
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class WishPage : Fragment(R.layout.page_wish) {

    private val binding by viewBinding(PageWishBinding::bind)
    private lateinit var myWishAdapter : MyWishAdapter


    override fun onViewCreated(view : View, savedInstanceState : Bundle?) = binding.scope{
        super.onViewCreated(view, savedInstanceState)

        myWishAdapter = MyWishAdapter(requireContext(),object :MyWishAdapter.OnWishItemTouchListener{
            override fun onWishClick() {
                Toast.makeText(requireContext(), "wish", Toast.LENGTH_SHORT).show()
            }

            override fun onPostClick() {
                findNavController().navigate(R.id.watchVideoFragment)

            }
        })


    }

}
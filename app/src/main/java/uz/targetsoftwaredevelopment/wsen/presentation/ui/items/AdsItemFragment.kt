package uz.targetsoftwaredevelopment.wsen.presentation.ui.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.entities.AdData
import uz.targetsoftwaredevelopment.wsen.databinding.ItemAdsBinding
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class AdsItemFragment : Fragment(R.layout.item_ads) {
    private val binding by viewBinding(ItemAdsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()
        val data = bundle.getSerializable("AD_DATA") as AdData

        vvAd.setVideoURI(data.videoUrl)
    }
}
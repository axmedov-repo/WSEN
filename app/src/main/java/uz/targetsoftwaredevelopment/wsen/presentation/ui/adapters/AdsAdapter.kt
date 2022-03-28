package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.targetsoftwaredevelopment.wsen.data.entities.AdData
import uz.targetsoftwaredevelopment.wsen.presentation.ui.items.AdsItemFragment
import uz.targetsoftwaredevelopment.wsen.utils.putArguments

class AdsAdapter(
    fragmentActivity: FragmentActivity,
    private val list: List<AdData>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment =
        AdsItemFragment().putArguments { putSerializable("AD_DATA", list[position]) }
}
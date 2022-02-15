package uz.targetsoftwaredevelopment.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.targetsoftwaredevelopment.myapplication.ui.fragments.ItemViewPagerFragment

class ItemViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 10
    }


    override fun getItem(position: Int): Fragment {
        return ItemViewPagerFragment()
    }

}
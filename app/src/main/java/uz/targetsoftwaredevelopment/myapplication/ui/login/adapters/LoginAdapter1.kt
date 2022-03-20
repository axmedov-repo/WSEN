package uz.targetsoftwaredevelopment.myapplication.ui.login.adapters

import android.content.Context
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.targetsoftwaredevelopment.myapplication.ui.login.LoginTabFragment
import uz.targetsoftwaredevelopment.myapplication.ui.login.SignUpTabFragment

class LoginAdapter(fm: FragmentManager?, private val context: Context, var totalTabs: Int) :
    FragmentPagerAdapter(
        fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int {
        return totalTabs
    }

    @NonNull
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginTabFragment()
            }
            else -> {
                SignUpTabFragment()
            }
        }
    }
}
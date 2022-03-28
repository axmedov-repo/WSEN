package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.ui.pages.LoginPage
import uz.targetsoftwaredevelopment.wsen.presentation.ui.pages.RegisterPage

class LoginScreenAdapter(fm: FragmentManager, lifecycle: Lifecycle, private val tabsCount: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = tabsCount

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> {
                LoginPage()
            }
            else -> {
                RegisterPage()
            }
        }
}
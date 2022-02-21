package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenBasicNavBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.BasicPageAdapter
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class BasicScreen : Fragment(R.layout.screen_basic_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenBasicNavBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BasicPageAdapter(childFragmentManager, lifecycle)

        innerLayout.apply {
            pager.adapter = adapter
            pager.isUserInputEnabled = false

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> innerLayout.pager.currentItem = 0
                    R.id.videos -> innerLayout.pager.currentItem = 1
                    R.id.add_video -> innerLayout.pager.currentItem = 2
                    R.id.favourite_videos -> innerLayout.pager.currentItem = 3
                    else -> innerLayout.pager.currentItem = 4
                }
                return@setOnItemSelectedListener true
            }

            btnMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = true
}
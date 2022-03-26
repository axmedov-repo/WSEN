package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenBasicNavBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.BasicPageAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.BasicScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl.BasicScreenViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope


@AndroidEntryPoint
class BasicScreen : Fragment(R.layout.screen_basic_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenBasicNavBinding::bind)
    private val viewModel: BasicScreenViewModel by viewModels<BasicScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BasicPageAdapter(childFragmentManager, lifecycle)
        adapter.setVideoClickListener {
            findNavController().navigate(
                BasicScreenDirections.actionBasicScreenToWatchVideoScreen(
                    it
                )
            )
        }

        innerLayout.apply {
            pager.adapter = adapter
            pager.isUserInputEnabled = false

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> innerLayout.pager.setCurrentItem(0, false)
                    R.id.videos -> innerLayout.pager.setCurrentItem(1, false)
                    R.id.add_video -> innerLayout.pager.setCurrentItem(2, false)
                    R.id.favourite_videos -> innerLayout.pager.setCurrentItem(3, false)
                    else -> innerLayout.pager.setCurrentItem(4, false)
                }
                return@setOnItemSelectedListener true
            }

            btnMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}
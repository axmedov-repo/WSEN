package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenBasicNavBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.BasicScreenAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.BasicScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl.BasicScreenViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.CheckInternetReceiver
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class BasicScreen : Fragment(R.layout.screen_basic_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenBasicNavBinding::bind)
    private val viewModel: BasicScreenViewModel by viewModels<BasicScreenViewModelImpl>()
    private val checkInternetReceiver = CheckInternetReceiver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BasicScreenAdapter(childFragmentManager, lifecycle)
        adapter.apply {
            setVideoClickListener {
                findNavController().navigate(
                    BasicScreenDirections.actionBasicScreenToWatchVideoScreen(
                        it
                    )
                )
            }
            setEditMyVideoClickedListener {
                findNavController().navigate(
                    BasicScreenDirections.actionBasicScreenToEditVideoScreen(
                        it
                    )
                )
            }

            setWatchMyVideoClickedListener {
                findNavController().navigate(
                    BasicScreenDirections.actionBasicScreenToWatchVideoScreen(
                        it
                    )
                )
            }
        }
        innerLayout.apply {
            pager.adapter = adapter
            pager.isUserInputEnabled = false

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> innerLayout.pager.setCurrentItem(0, false)
                    R.id.videos -> innerLayout.pager.setCurrentItem(1, false)
                    R.id.add_video -> innerLayout.pager.setCurrentItem(2, false)
                    R.id.my_videos -> innerLayout.pager.setCurrentItem(3, false)
                    else -> innerLayout.pager.setCurrentItem(4, false)
                }
                return@setOnItemSelectedListener true
            }

            btnMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this@BasicScreen)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_wish_list -> {
                findNavController().navigate(BasicScreenDirections.actionBasicScreenToFavouriteVideosScreen())
            }
            R.id.nav_language -> {
                findNavController().navigate(BasicScreenDirections.actionBasicScreenToLanguageScreen())
            }
            R.id.nav_invite_friends -> {
//                Toast.makeText(requireContext() , "invite friends" , Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_SUBJECT, "Android app")
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=${activity?.packageName}"
                )
                intent.type = "text/plain"
                startActivity(intent)

            }
            R.id.nav_rate_our_app -> {
//                Toast.makeText(requireContext() , "rate our app" , Toast.LENGTH_SHORT).show()
                val uri: Uri = Uri.parse("market://details?id=${activity?.packageName}")
                val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                goToMarket.addFlags(
                    Intent.FLAG_ACTIVITY_NO_HISTORY or
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                )
                try {
                    startActivity(goToMarket)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=${activity?.packageName}")
                        )
                    )
                }
            }
            R.id.nav_logout -> {
                Toast.makeText(requireContext(), "log out", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onStart() {
        val filter: IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(checkInternetReceiver, filter)
        super.onStart()
    }

    override fun onDestroy() {
        requireContext().unregisterReceiver(checkInternetReceiver)
        super.onDestroy()
    }
}
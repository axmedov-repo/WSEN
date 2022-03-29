package uz.targetsoftwaredevelopment.wsen.presentation.ui.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LogoutResponse
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenBasicNavBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.BasicScreenAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.ui.dialog.ClarifyLogoutDialog
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.BasicScreenViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl.BasicScreenViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.CheckInternetReceiver
import uz.targetsoftwaredevelopment.wsen.utils.scope

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

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isOpen) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        requireActivity().finish()
                    }
                }
            })

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
            if (navigationView != null) {
                navigationView.setNavigationItemSelectedListener(this@BasicScreen)
            }
        }

        viewModel.logoutUserResponseLiveData.observe(viewLifecycleOwner, logoutUserObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val logoutUserObserver = Observer<LogoutResponse> {
        findNavController().navigate(BasicScreenDirections.actionBasicScreenToAuthScreen())
    }
    private val errorObserver = Observer<String> { errorMessage ->
        if (errorMessage.equals(getString(R.string.internet_disconnected))) {
        } else {
            FancyToast.makeText(
                requireContext(), errorMessage,
                FancyToast.LENGTH_LONG, FancyToast.WARNING, true
            ).show()
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
                val clarifyLogoutDialog = ClarifyLogoutDialog()
                clarifyLogoutDialog.setPositiveBtnListener {
                    viewModel.logoutUser()
                }
                clarifyLogoutDialog.show(childFragmentManager, "logout_dialog")
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

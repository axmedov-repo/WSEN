package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenHomeBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.HomeScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.impl.HomeScreenViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home), OnMapReadyCallback,
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeScreenViewModel by viewModels<HomeScreenViewModelImpl>()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawerLayout = view.findViewById(R.id.drawer_layout)
        navigationView = view.findViewById(R.id.navigation_view)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                        drawerLayout.closeDrawer(GravityCompat.END)
                    }
                }
            })

//        navigationView.setCheckedItem()
        navigationDrawer()


        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapVolunteers) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val uzbekistan = LatLng(43.55649699970902, 59.948396335930944)
        map.addMarker(MarkerOptions().position(uzbekistan).title("Uzbekistan"))
        map.moveCamera(CameraUpdateFactory.newLatLng(uzbekistan))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun navigationDrawer() {
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)

        binding.btnMenu.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
    }

}

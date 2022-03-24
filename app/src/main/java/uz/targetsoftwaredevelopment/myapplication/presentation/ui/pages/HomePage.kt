package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.MarginPageTransformer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.entities.AdData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.Statistics
import uz.targetsoftwaredevelopment.myapplication.databinding.PageHomeBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.AdsAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.HomePageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.HomePageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.dpToPx
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class HomePage : Fragment(R.layout.page_home), OnMapReadyCallback {
    private val binding by viewBinding(PageHomeBinding::bind)
    private val viewModel: HomePageViewModel by viewModels<HomePageViewModelImpl>()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var map: GoogleMap
    private var adsList = ArrayList<AdData>()
    private lateinit var adsAdapter: AdsAdapter
    private var isFirstTime: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHomePageData()


        /* drawerLayout = view.findViewById(R.id.drawer_layout)
         navigationView = view.findViewById(R.id.navigation_view)*/

        /*   requireActivity().onBackPressedDispatcher
               .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                   override fun handleOnBackPressed() {
                       if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                           drawerLayout.closeDrawer(GravityCompat.END)
                       }
                   }
               })*/

//        navigationView.setCheckedItem()
        /*   navigationDrawer()
           onClickListener()
   */

        /*   val mapFragment =
               childFragmentManager.findFragmentById(R.id.mapVolunteers) as SupportMapFragment
           mapFragment.getMapAsync(this)*/


        if (isFirstTime) {
            fillAdsList()
            isFirstTime = false
        }

        adsAdapter = AdsAdapter(requireActivity(), adsList)
        pagerAds.apply {
            adapter = adsAdapter
            clipToPadding = false   // allow full width shown with padding
            clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 2  // make sure left/right item is rendered
        }
        // increase this offset to show more of left/right
        val offsetPx = 16.dpToPx(resources.displayMetrics)
        pagerAds.setPadding(offsetPx, 0, offsetPx, 0)

        // increase this offset to increase distance between 2 items
        val pageMarginPx = 2.dpToPx(resources.displayMetrics)
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        pagerAds.setPageTransformer(marginTransformer)

        lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(4000L)
                if (pagerAds.currentItem == adsList.size - 1) {
                    pagerAds.currentItem = 0
                } else {
                    pagerAds.currentItem += 1
                }
            }
        }

        viewModel.statisticsLiveData.observe(viewLifecycleOwner, statisticsObserver)
//        viewModel.categoriesLiveData.observe(viewLifecycleOwner, categoriesObserver)
    }

    private val statisticsObserver = Observer<Statistics> {
        binding.txtHeaderVolunteers.text = "${it.allVolunteers}"
    }

    private val categoriesObserver = Observer<List<CategoriesItem?>?> {
        Glide.with(binding.imgBgEcoVideos.context)
            .load(it[0]!!.icon)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(binding.imgBgEcoVideos)
        binding.ecoVideoTv.text = it[0]!!.name

        Glide.with(binding.imgBgMyVideos.context)
            .load(it[1]!!.icon)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(binding.imgBgMyVideos)
        binding.myVideoTv.text = it[1]!!.name

        Glide.with(binding.imgBgAllVideos.context)
            .load(it[2]!!.icon)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(binding.imgBgAllVideos)
        binding.allVideoTv.text = it[2]!!.name
    }

    /* private fun onClickListener() {
         binding.apply {

             allVideosCv.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("Title","All videos")
                 findNavController().navigate(R.id.videosItemFragment,bundle)
             }

             ecoVideosCv.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("Title","Ecology videos")
                 findNavController().navigate(R.id.videosItemFragment,bundle)
             }

             myVideosCv.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("Title","My Videos")
                 findNavController().navigate(R.id.myVideosFragment,bundle)
             }

         }
     }*/

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val uzbekistan = LatLng(43.55649699970902, 59.948396335930944)
        map.addMarker(MarkerOptions().position(uzbekistan).title("Uzbekistan"))
        map.moveCamera(CameraUpdateFactory.newLatLng(uzbekistan))
    }

    private fun fillAdsList() {

    }

    /* override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.nav_profile->{
                 findNavController().navigate(R.id.profileFragment)
             }
             R.id.nav_add_video->{
                 findNavController().navigate(R.id.recordVideoFragment)

             }
             R.id.nav_language->{
                 findNavController().navigate(R.id.languageFragment)
             }
             R.id.nav_logout->{
                 Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
             }


         }
         return true
     }*/

    /* private fun navigationDrawer() {
         navigationView.bringToFront()
         navigationView.setNavigationItemSelectedListener(this)

         binding.btnMenu.setOnClickListener {
             if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                 drawerLayout.closeDrawer(GravityCompat.END)
             } else {
                 drawerLayout.openDrawer(GravityCompat.END)
             }
         }
     }*/
}

package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.entities.AdData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.Statistics
import uz.targetsoftwaredevelopment.wsen.databinding.PageHomeBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.AdsAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.HomePageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.HomePageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.getImageFile
import uz.targetsoftwaredevelopment.wsen.utils.scope

@AndroidEntryPoint
class HomePage : Fragment(R.layout.page_home), OnMapReadyCallback {
    private val binding by viewBinding(PageHomeBinding::bind)
    private val viewModel: HomePageViewModel by viewModels<HomePageViewModelImpl>()
    private lateinit var map: GoogleMap
    private var adsList = ArrayList<AdData>()
    private lateinit var adsAdapter: AdsAdapter
    private var isFirstTime: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHomePageData()
        if (isFirstTime) {
            fillAdsList()
            isFirstTime = false
        }

//        nestedScrollView.scrollTo(0,0)

        adsAdapter = AdsAdapter(requireActivity(), adsList)
        pagerAds.apply {
            adapter = adsAdapter
            //  clipToPadding = false   // allow full width shown with padding
            //  clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 2  // make sure left/right item is rendered
        }
        /*  // increase this offset to show more of left/right
          val offsetPx = 16.dpToPx(resources.displayMetrics)
          pagerAds.setPadding(offsetPx, 0, offsetPx, 0)

          // increase this offset to increase distance between 2 items
          val pageMarginPx = 2.dpToPx(resources.displayMetrics)
          val marginTransformer = MarginPageTransformer(pageMarginPx)
          pagerAds.setPageTransformer(marginTransformer)*/

        lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(4000L)
                if (pagerAds.currentItem == adsList.size - 1) {
                    pagerAds.setCurrentItem(0, false)
                } else {
                    pagerAds.currentItem += 1
                }
            }
        }

        viewModel.statisticsLiveData.observe(viewLifecycleOwner, statisticsObserver)
//        viewModel.categoriesLiveData.observe(viewLifecycleOwner, categoriesObserver)
    }

    private val statisticsObserver = Observer<Statistics> {
        binding.txtHeaderVolunteers.text = "${it.all_volunteers}"
    }

    private val categoriesObserver = Observer<List<CategoriesItem?>?> {
        /* Glide.with(binding.imgBgEcoVideos.context)
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
         binding.allVideoTv.text = it[2]!!.name*/
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val uzbekistan = LatLng(43.55649699970902, 59.948396335930944)
        map.addMarker(MarkerOptions().position(uzbekistan).title("Uzbekistan"))
        map.moveCamera(CameraUpdateFactory.newLatLng(uzbekistan))
    }

    private fun fillAdsList() {
        adsList.apply {
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo1
                )
            )
            add(
                AdData(
                    R.drawable.ad_photo2
                )
            )
        }
    }
}

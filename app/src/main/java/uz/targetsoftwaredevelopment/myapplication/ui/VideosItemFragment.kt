package uz.targetsoftwaredevelopment.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import uz.targetsoftwaredevelopment.myapplication.adapters.SliderAdapter
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentVideosItemBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VideosItemFragment : Fragment() {

    private lateinit var binding:FragmentVideosItemBinding
    private lateinit var sliderAdapter: SliderAdapter
    var imgList = arrayListOf(1,2,3,5,6,7,8,9,0)

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosItemBinding.inflate(inflater,container,false)

        binding.apply {
            sliderAdapter = SliderAdapter(1)
            rv.adapter = sliderAdapter
            rv.set3DItem(false)
            rv.setAlpha(false)
            rv.setInfinite(true)
            rv.setFlat(false)
            rv.setIsScrollingEnabled(true)
            rv.setIntervalRatio(0.65f)


//            val carouselLayoutManager = rv.getCarouselLayoutManager()

        }

//        binding.apply {
//            sliderAdapter = SliderAdapter(1)
//            viewPager.adapter = sliderAdapter
//            viewPager.clipToPadding = false
////            viewPager.setPadding(60,0,60,0)
////            viewPager.setBackgroundColor(Color.TRANSPARENT)
//            viewPager.offscreenPageLimit = 3
////            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
//            val compositePageTrans = CompositePageTransformer()
//            compositePageTrans.addTransformer(MarginPageTransformer(30))
//            compositePageTrans.addTransformer { page, position ->
//                val r: Float = 1 - kotlin.math.abs(position)
//                page.scaleY = 0.85f + r * 0.15f
//            }
//            viewPager.setPageTransformer(compositePageTrans)
//
//        }


//        binding.addVideoCv.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.com))
//        binding.addVideoBtn.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.com))
//        binding.add.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.com))


        return binding.root
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideosItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
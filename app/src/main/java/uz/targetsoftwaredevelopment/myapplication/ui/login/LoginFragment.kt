package uz.targetsoftwaredevelopment.myapplication.ui.login

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentLoginBinding
import uz.targetsoftwaredevelopment.myapplication.ui.login.adapters.LoginAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginAdapter: LoginAdapter
    var v: Float = 0F
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.apply {

            tabLayout.visibility
            tabLayout.addTab(tabLayout.newTab().setText("LOGIN"))
            tabLayout.addTab(tabLayout.newTab().setText("SIGN UP"))
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL

//        tabLayout.setTabTextColors(resources.getColor(R.color.darker_gray),
//            resources.getColor(R.color.holo_green_dark));

            val loginAdapter =
                LoginAdapter(childFragmentManager, requireContext(), tabLayout.tabCount)
            viewPager.adapter = loginAdapter

//        TabLayoutMediator(tabLayout ,viewPager  , true) { tab , index ->
//            val titleList = ArrayList<String>()
//            titleList.add("LOGIN")
//            titleList.add("SIGN UP")
//            val title = titleList[index]
//            tab.text  = title
//        }.attach()


//        tabLayout.setupWithViewPager(viewPager);


            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                    val position = tab.position
                    if (position == 0) {
                        tv1.setTextColor(Color.parseColor("#727272"))
                        tv2.setTextColor(Color.parseColor("#FF000000"))
                    } else {
                        tv2.setTextColor(Color.parseColor("#727272"))
                        tv1.setTextColor(Color.parseColor("#FF000000"))
                    }


//                tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
//                tabLayout.setSelectedTabIndicatorHeight( ((5 * resources.displayMetrics.density).toInt()));
//                tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));


                }

                override fun onTabUnselected(tab: TabLayout.Tab) {


//                tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
//                tabLayout.setSelectedTabIndicatorHeight((int) (5 * resources.displayMetrics.density));
//                tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));


//                val tabView = tab.customView
//                val linearLayout = tabView!!.findViewById<LinearLayout>(R.id.l1)
//                val textView = tabView!!.findViewById<TextView>(R.id.text)
//                textView.setTextColor(Color.BLACK)
//                linearLayout.setBackgroundColor(Color.parseColor("#E5E5E5"))


//                tabLayout.setTabTextColors(resources.getColor(R.color.darker_gray),
//                    resources.getColor(R.color.white));

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })

            cardPlaymarket.setOnClickListener {

                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=com.gamefirst.free.strategy.save.the.earth"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }

            cardYouTube.setOnClickListener {
                val uri =
                    Uri.parse("https://www.youtube.com/watch?v=aTrWtFR_FrQ")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                }
            }

            cardTelegram.setOnClickListener {
                val uri =
                    Uri.parse("https://t.me/ecology_problems")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                }
            }



            cardPlaymarket.translationY = 300.0F
            cardYouTube.translationY = 300.0F
            cardTelegram.translationY = 300.0F

            cardPlaymarket.alpha = 0F
            cardYouTube.alpha = v
            cardTelegram.alpha = 0F
            tabLayout.alpha = 0F

            cardPlaymarket.animate().translationY(0F).alpha(1F).duration = 1000
            cardPlaymarket.animate().setStartDelay(400).start()
            cardYouTube.animate().translationY(0F).alpha(1F).duration = 1000
            cardYouTube.animate().setStartDelay(600).start()
            cardTelegram.animate().translationY(0F).alpha(1F).duration = 1000
            cardTelegram.animate().setStartDelay(800).start()


        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


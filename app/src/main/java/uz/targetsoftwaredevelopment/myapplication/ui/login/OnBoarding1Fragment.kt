package uz.targetsoftwaredevelopment.myapplication.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentOnBoarding1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnBoardingFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnBoardingFragment1 : Fragment() {
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

    private lateinit var binding: FragmentOnBoarding1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoarding1Binding.inflate(layoutInflater, container, false)


        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                /*findNavController().popBackStack()
                findNavController().navigate(R.id.onBoarding2Fragment)*/
                findNavController().navigate(OnBoardingFragment1Directions.actionOnBoardingFragment1ToBasicScreen())
            },
            1500
        )
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnBoardingFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
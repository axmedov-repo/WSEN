package uz.targetsoftwaredevelopment.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.targetsoftwaredevelopment.myapplication.MainActivity
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ActivityLoginBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentOnBoarding3Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OnBoarding3Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentOnBoarding3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)

//       binding.floatBtn.setOnClickListener {
//           var intent =Intent(requireContext(),MainActivity::class.java)
//           startActivity(intent)
//       }

        binding.apply {

        }

        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                findNavController().popBackStack()
                findNavController().navigate(R.id.loginFragment)
            },
            1500
        )

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnBoarding3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
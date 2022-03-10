package uz.targetsoftwaredevelopment.myapplication.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentLoginTabBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginTabFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentLoginTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginTabBinding.inflate(layoutInflater, container, false)
        binding.email.translationX = 0F
        binding.password.translationX = 0F
        binding.text.translationX = 0F
        binding.btn.translationX = 0F

        binding.email.alpha = 0F
        binding.password.alpha = 0F
        binding.text.alpha = 0F
        binding.btn.alpha = 0F


        binding.email.animate().translationY(0F).alpha(1F).duration = 1000
        binding.email.animate().setStartDelay(300).start()
        binding.password.animate().translationY(0F).alpha(1F).duration = 1000
        binding.password.animate().setStartDelay(500).start()
        binding.text.animate().translationY(0F).alpha(1F).duration = 1000
        binding.text.animate().setStartDelay(700).start()
        binding.btn.animate().translationY(0F).alpha(1F).duration = 1000
        binding.btn.animate().setStartDelay(900).start()


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
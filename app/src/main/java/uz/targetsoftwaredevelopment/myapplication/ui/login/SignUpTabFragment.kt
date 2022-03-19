package uz.targetsoftwaredevelopment.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentSignUpTabBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.MainActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignUpTabFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentSignUpTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpTabBinding.inflate(layoutInflater, container, false)

        binding.email.translationX = 0F
        binding.password.translationX = 0F
        binding.confirmPassword.translationX = 0F
        binding.number.translationX = 0F
        binding.btn.translationX = 0F

        binding.email.alpha = 0F
        binding.password.alpha = 0F
        binding.number.alpha = 0F
        binding.confirmPassword.alpha = 0F
        binding.btn.alpha = 0F


        binding.email.animate().translationY(0F).alpha(1F).duration = 1000
        binding.email.animate().setStartDelay(300).start()
        binding.password.animate().translationY(0F).alpha(1F).duration = 1000
        binding.password.animate().setStartDelay(500).start()
        binding.confirmPassword.animate().translationY(0F).alpha(1F).duration = 1000
        binding.confirmPassword.animate().setStartDelay(700).start()
        binding.btn.animate().translationY(0F).alpha(1F).duration = 1000
        binding.btn.animate().setStartDelay(900).start()
        binding.number.animate().translationY(0F).alpha(1F).duration = 1000
        binding.number.animate().setStartDelay(900).start()

        binding.btn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
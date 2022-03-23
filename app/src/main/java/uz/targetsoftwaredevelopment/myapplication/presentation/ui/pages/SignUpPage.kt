package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.PageSignUpBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.MainActivity
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.SignUpPageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.SignUpPageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class SignUpPage : Fragment(R.layout.page_sign_up) {
    private val binding by viewBinding(PageSignUpBinding::bind)
    private val viewModel: SignUpPageViewModel by viewModels<SignUpPageViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        email.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                setStartDelay(300).start()
                translationY(0F).alpha(1F).duration = 1000
            }
        }

        password.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(500).start()
            }
        }

        confirmPassword.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(700).start()
            }
        }

        number.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(900).start()
            }
        }

        btn.apply {
            translationX = 0F
            alpha = 0F
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(900).start()
            setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.databinding.PageRegisterBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.SignUpPageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.SignUpPageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.getImageFile
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class RegisterPage : Fragment(R.layout.page_register) {
    private val binding by viewBinding(PageRegisterBinding::bind)
    private val viewModel: SignUpPageViewModel by viewModels<SignUpPageViewModelImpl>()
    private var isReadyFirstname = false
    private var isReadyLastname = false
    private var isReadyEmail = false
    private var isReadyNumber = false
    private var isReadyPassword = false
    private var isReadyConfirmPassword = false

    private var registerBtnClickListener: ((RegisterUserRequest) -> Unit)? = null
    fun setRegisterBtnClickListener(f: (RegisterUserRequest) -> Unit) {
        registerBtnClickListener = f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        etFirstName.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(100).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyFirstname = it.isNotEmpty()
                    check()
                }
            }
        }

        etLastName.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(200).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyLastname = it.isNotEmpty()
                    check()
                }
            }
        }

        etEmail.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(300).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyEmail = it.isNotEmpty() && it.contains("@gmail.com")
                    check()
                }
            }
        }

        etPassword.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(400).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyPassword = it.isNotEmpty()
                    check()
                }
            }
        }

        etConfirmPassword.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(500).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyConfirmPassword = it.toString() == etPassword.text.toString()
                    check()
                }
            }
        }

        etNumber.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(600).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyNumber = it.isNotEmpty()
                    check()
                }
            }
        }

        btnRegister.apply {
            translationX = 0F
            alpha = 0F
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(600).start()
            setOnClickListener {
                registerBtnClickListener?.invoke(
                    RegisterUserRequest(
                        "",
                        etPassword.text.toString(),
                        etConfirmPassword.text.toString(),
                        etFirstName.text.toString(),
                        etLastName.text.toString(),
                        getImageFile(requireContext(), R.drawable.default_profile_img),
                        etEmail.text.toString()
                    )
                )
            }
        }
    }

    private fun check() {
        binding.btnRegister.isEnabled =
            isReadyFirstname && isReadyLastname &&
                    isReadyEmail && isReadyNumber &&
                    isReadyPassword && isReadyConfirmPassword
    }
}
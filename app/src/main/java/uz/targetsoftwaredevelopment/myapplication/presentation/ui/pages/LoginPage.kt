package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.databinding.PageLoginBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.LoginPageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.LoginPageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class LoginPage : Fragment(R.layout.page_login) {
    private val binding by viewBinding(PageLoginBinding::bind)
    private val viewModel: LoginPageViewModel by viewModels<LoginPageViewModelImpl>()
    private var isReadyUsername = false
    private var isReadyPassword = false

    private var loginBtnClickListener: ((LoginUserRequest) -> Unit)? = null
    fun setLoginBtnClickListener(f: (LoginUserRequest) -> Unit) {
        loginBtnClickListener = f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        etUsername.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(300).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyUsername = it.isNotEmpty()
                    check()
                }
            }
        }

        etPassword.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(500).start()
            }
            addTextChangedListener {
                it?.let {
                    isReadyPassword = it.isNotEmpty()
                    check()
                }
            }
        }

        txtForgetPassword.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(700).start()
            }
        }

        btnLogin.apply {
            translationX = 0F
            alpha = 0F
            animate().apply {
                translationY(0F).alpha(1F).duration = 1000
                setStartDelay(900).start()
            }

            setOnClickListener {
                loginBtnClickListener?.invoke(
                    LoginUserRequest(
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun check() {
        binding.btnLogin.isEnabled =
            isReadyPassword && isReadyUsername
    }
}

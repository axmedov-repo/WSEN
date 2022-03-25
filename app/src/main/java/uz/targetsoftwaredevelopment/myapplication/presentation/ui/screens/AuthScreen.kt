package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenAuthBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.AuthScreenAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.AuthScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl.AuthScreenViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.gone
import uz.targetsoftwaredevelopment.myapplication.utils.scope
import uz.targetsoftwaredevelopment.myapplication.utils.showToast
import uz.targetsoftwaredevelopment.myapplication.utils.visible

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenAuthBinding::bind)
    private val viewModel: AuthScreenViewModel by viewModels<AuthScreenViewModelImpl>()
    private lateinit var authAdapter: AuthScreenAdapter
    private var v: Float = 0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        authAdapter = AuthScreenAdapter(childFragmentManager, lifecycle, tabLayout.tabCount)
        viewPager.adapter = authAdapter
        authAdapter.setRegisterBtnClickListener { registerData ->
            progressBar.animate()
            progressBar.visible()
            viewModel.registerUser(registerData)
        }
        authAdapter.setLoginBtnClickListener { loginData ->
            progressBar.animate()
            progressBar.visible()
            viewModel.loginUser(loginData)
        }

        tabLayout.apply {
            addTab(tabLayout.newTab().setText("LOGIN"))
            addTab(tabLayout.newTab().setText("SIGN UP"))
            tabGravity = TabLayout.GRAVITY_FILL
            alpha = 0F
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->

        }.attach()

        cardPlaymarket.apply {
            setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=com.gamefirst.free.strategy.save.the.earth"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
            translationY = 300.0F
            alpha = 0F
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(400).start()
        }

        cardYouTube.apply {
            setOnClickListener {
                val uri =
                    Uri.parse("https://www.youtube.com/watch?v=aTrWtFR_FrQ")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                }
            }
            translationY = 300.0F
            alpha = v
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(600).start()
        }

        cardTelegram.apply {
            setOnClickListener {
                val uri =
                    Uri.parse("https://t.me/ecology_problems")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                }
            }
            translationY = 300.0F
            alpha = 0F
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(800).start()
        }

        viewModel.registerUserResponseLiveData.observe(
            viewLifecycleOwner,
            registerUserResponseObserver
        )
        viewModel.loginUserResponseLiveData.observe(
            viewLifecycleOwner,
            loginUserResponseObserver
        )
    }

    private val registerUserResponseObserver = Observer<RegisterUserResponse> {
        binding.progressBar.gone()
        binding.progressBar.clearAnimation()
        showToast("Login")
        binding.viewPager.currentItem = 1
    }
    private val loginUserResponseObserver = Observer<LoginUserResponse> {
        binding.progressBar.gone()
        binding.progressBar.clearAnimation()
        findNavController().navigate(AuthScreenDirections.actionAuthScreenToBasicScreen())
    }
}

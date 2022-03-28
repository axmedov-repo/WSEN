package uz.targetsoftwaredevelopment.wsen.presentation.ui.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.wsen.databinding.ScreenAuthBinding
import uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters.AuthScreenAdapter
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.AuthScreenViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl.AuthScreenViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.gone
import uz.targetsoftwaredevelopment.wsen.utils.scope
import uz.targetsoftwaredevelopment.wsen.utils.visible

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenAuthBinding::bind)
    private val viewModel: AuthScreenViewModel by viewModels<AuthScreenViewModelImpl>()
    private lateinit var authAdapter: AuthScreenAdapter
    private var v: Float = 0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        authAdapter = AuthScreenAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = authAdapter

        authAdapter.apply {
            setRegisterBtnClickListener { registerData ->
                progressBar.animate()
                progressBar.visible()
                viewModel.registerUser(registerData)
            }

            setLoginBtnClickListener { loginData ->
                progressBar.animate()
                progressBar.visible()
                viewModel.loginUser(loginData)
            }
        }

        /*tabLayout.apply {
            addTab(tabLayout.newTab().setText("Login"))
            addTab(tabLayout.newTab().setText("Register"))
            tabGravity = TabLayout.GRAVITY_FILL
            alpha = 0F
        }*/

//        TabLayoutMediator(tabLayout , viewPager) { tab , pos ->
//            tab.text = "Login"
//            /* if (pos == 0) {
//                 titleLogin.setTextColor(
//                     ContextCompat.getColor(
//                         requireContext(),
//                         R.color.main_green
//                     )
//                 )
//                 titleRegister.setTextColor(
//                     ContextCompat.getColor(
//                         requireContext(),
//                         R.color.black
//                     )
//                 )
//             } else {
//                 titleLogin.setTextColor(
//                     ContextCompat.getColor(
//                         requireContext(),
//                         R.color.black
//                     )
//                 )
//                 titleRegister.setTextColor(
//                     ContextCompat.getColor(
//                         requireContext(),
//                         R.color.main_green
//                     )
//                 )
//             }*/
//        }.attach()

        titleLogin.setOnClickListener {
            viewPager.currentItem = 0
        }
        titleRegister.setOnClickListener {
            viewPager.currentItem = 1
        }

        cardPlaymarket.apply {
            setOnClickListener {
                val uri: Uri = Uri.parse("market://details?id=${activity?.packageName}")
                val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                goToMarket.addFlags(
                    Intent.FLAG_ACTIVITY_NO_HISTORY or
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                )
                try {
                    startActivity(goToMarket)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=${activity?.packageName}")
                        )
                    )
                }
            }
            translationY = 300.0F
            alpha = 0F
            animate().translationY(0F).alpha(1F).duration = 1000
            animate().setStartDelay(400).start()
        }

        cardYouTube.apply {
            setOnClickListener {
                val uri =
                    Uri.parse(getString(R.string.you_tube_video_link))
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
                    Uri.parse(getString(R.string.telegram_kanal_link))
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
        viewModel.errorLiveData.observe(
            viewLifecycleOwner,
            errorObserver
        )
    }

    private val registerUserResponseObserver = Observer<RegisterUserResponse> {
        binding.progressBar.gone()
        binding.progressBar.clearAnimation()
//        showToast("Login")
        FancyToast.makeText(
            requireContext(),
            getString(R.string.successfully_registered),
            FancyToast.LENGTH_LONG,
            FancyToast.SUCCESS,
            true
        ).show()
        binding.viewPager.currentItem = 0
    }
    private val loginUserResponseObserver = Observer<LoginUserResponse> {
        binding.progressBar.gone()
        binding.progressBar.clearAnimation()
        FancyToast.makeText(
            requireContext(),
            getString(R.string.successfully_logged_in),
            FancyToast.LENGTH_LONG,
            FancyToast.SUCCESS,
            true
        ).show()
        findNavController().navigate(AuthScreenDirections.actionAuthScreenToBasicScreen())
    }
    private val errorObserver = Observer<String> {
        binding.progressBar.gone()
        binding.progressBar.clearAnimation()
        FancyToast.makeText(
            requireContext(),
            getString(R.string.error_reg_login),
            FancyToast.LENGTH_LONG,
            FancyToast.ERROR,
            true
        ).show()
    }
}

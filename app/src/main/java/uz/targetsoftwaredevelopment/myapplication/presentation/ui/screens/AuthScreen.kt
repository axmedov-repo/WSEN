package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenAuthBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.LoginScreenAdapter
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenAuthBinding::bind)
    private lateinit var loginAdapter: LoginScreenAdapter
    private var v: Float = 0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        tabLayout.apply {
            addTab(tabLayout.newTab().setText("LOGIN"))
            addTab(tabLayout.newTab().setText("SIGN UP"))
            tabGravity = TabLayout.GRAVITY_FILL
            alpha = 0F
        }

        val loginAdapter = LoginScreenAdapter(childFragmentManager, lifecycle, tabLayout.tabCount)
        viewPager.adapter = loginAdapter
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
    }
}

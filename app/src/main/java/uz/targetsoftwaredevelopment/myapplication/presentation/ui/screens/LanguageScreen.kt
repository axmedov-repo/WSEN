package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenLanguageBinding

@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_language) {
    private val binding by viewBinding(ScreenLanguageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
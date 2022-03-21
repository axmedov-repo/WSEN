package uz.targetsoftwaredevelopment.myapplication.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenEditVideoBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class EditVideoScreen:Fragment(R.layout.screen_edit_video) {

    private val binding by viewBinding(ScreenEditVideoBinding::bind)

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)=binding.scope {
        super.onViewCreated(view, savedInstanceState)
    }
}
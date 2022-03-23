package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentEditVideoScreenBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.EditVideoScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl.EditVideoScreenViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class EditVideoScreen : Fragment(R.layout.fragment_edit_video_screen) {
    private val binding by viewBinding(FragmentEditVideoScreenBinding::bind)
    private val viewModel: EditVideoScreenViewModel by viewModels<EditVideoScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

    }
}
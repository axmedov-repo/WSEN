package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.databinding.DialogDeleteBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.PageMyPostsBinding
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters.MyPostsAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.MyPostsPageViewModel
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl.MyPostsPageViewModelImpl
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class MyPostsPage : Fragment(R.layout.page_my_posts) {
    private val binding by viewBinding(PageMyPostsBinding::bind)
    private val viewModel: MyPostsPageViewModel by viewModels<MyPostsPageViewModelImpl>()

    private lateinit var myPostsAdapter: MyPostsAdapter
    private var list = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

    override fun onViewCreated(view : View, savedInstanceState:Bundle?)=binding.scope{
        super.onViewCreated(view, savedInstanceState)

        myPostsAdapter = MyPostsAdapter(requireContext(),object :MyPostsAdapter.OnPostItemTouchListener{
            override fun onMenuEdit(videoData : VideoData) {
                findNavController().navigate(R.id.editVideoScreen)
            }
            override fun onPostClick(videoData : VideoData) {
                findNavController().navigate(R.id.watchVideoScreen)
            }
            override fun onMenuDelete(videoData : VideoData) {
                val deleteDialog = AlertDialog.Builder(requireContext())
                val dialogDeleteBinding = DialogDeleteBinding.inflate(layoutInflater)
                deleteDialog.setView(dialogDeleteBinding.root)

                val deleteBuilder = deleteDialog.create()
                deleteBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)

                dialogDeleteBinding.apply {

                    cancelPostsCv.setOnClickListener {
                        Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
                        deleteBuilder.cancel()
                    }

                    deletePostCv.setOnClickListener {
                        // bu yerga postni ochirish kodi yoziladi
                        Toast.makeText(requireContext(), "delete post", Toast.LENGTH_SHORT).show()
                        deleteBuilder.cancel()
                    }
                }

                deleteBuilder.show()
            }

            override fun onMenuWaiting(videoData : VideoData) {
                TODO("Not yet implemented")
            }

            override fun onMenuFinished(videoData : VideoData) {
                TODO("Not yet implemented")
            }
        })
//        myPostsAdapter.submitList(list)
        myVideosRv.adapter = myPostsAdapter

    }

}
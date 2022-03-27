package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages.*

class BasicScreenAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private var clickHomeButtonListener: (() -> Unit)? = null
    fun setOnClickHomeButtonListener(block: () -> Unit) {
        clickHomeButtonListener = block
    }

    private var videoClickListener: ((VideoData) -> Unit)? = null
    fun setVideoClickListener(block: (VideoData) -> Unit) {
        videoClickListener = block
    }

    private var editVideoClickedListener: ((VideoData) -> Unit)? = null
    fun setEditMyVideoClickedListener(f: (VideoData) -> Unit) {
        editVideoClickedListener = f
    }

    private var watchVideoClickedListener: ((VideoData) -> Unit)? = null
    fun setWatchMyVideoClickedListener(f: (VideoData) -> Unit) {
        watchVideoClickedListener = f
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        /* val paymentPage = PaymentPage()
         val bundle = Bundle()
         bundle.putString("direction_type", PaymentPageEnum.FROM_BASE_SCREEN.name)
         paymentPage.arguments = bundle*/

        return when (position) {
            0 -> HomePage()
            1 -> AllVideoPage().apply {
                setVideoClickedListener {
                    videoClickListener?.invoke(it)
                }
            }
            2 -> AddVideoPage()
            3 -> MyPostsPage().apply {
                setEditMyVideoClickedListener {
                    editVideoClickedListener?.invoke(it)
                }
                setWatchMyVideoClickedListener {
                    watchVideoClickedListener?.invoke(it)
                }
            }
            else -> ProfilePage()
        }
    }
}
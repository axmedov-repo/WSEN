package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages.*

class BasicPageAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    private var clickHomeButtonListener: (() -> Unit)? = null
    fun setOnClickHomeButtonListener(block: () -> Unit) {
        clickHomeButtonListener = block
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        /* val paymentPage = PaymentPage()
         val bundle = Bundle()
         bundle.putString("direction_type", PaymentPageEnum.FROM_BASE_SCREEN.name)
         paymentPage.arguments = bundle*/

        return when (position) {
            0 -> HomePage()
            1 -> AllVideoPage()
            2 -> AddVideoPage()
            3 -> MyPostsPage()
            else -> ProfilePage()
        }
    }
}
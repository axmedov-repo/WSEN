package uz.targetsoftwaredevelopment.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenLoginBinding

class LoginScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        findNavController().navigate(R.id.login_nav_host_fragment)
    }
}

/**
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import uz.targetsoftwaredevelopment.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
private lateinit var binding: ActivityLoginBinding
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
binding = ActivityLoginBinding.inflate(layoutInflater)
setContentView(binding.root)
}

override fun onSupportNavigateUp(): Boolean {
return findNavController(R.id.login_nav_host_fragment).navigateUp()
}
}
 */
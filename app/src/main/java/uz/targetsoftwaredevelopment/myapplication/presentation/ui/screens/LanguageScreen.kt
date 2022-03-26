package uz.targetsoftwaredevelopment.myapplication.presentation.ui.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.ScreenLanguageBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope


@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_language) {

    private val binding by viewBinding(ScreenLanguageBinding::bind)
    private val LOCALE_KEY = "localekey"
    private val UZBEK_LOCALE = "uz"
    private val ENGLISH_LOCALE = "en"
    private lateinit var sharedPreferences : SharedPreferences


    override fun onViewCreated(view : View , savedInstanceState : Bundle?)=binding.scope {
        super.onViewCreated(view , savedInstanceState)

        languageToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        sharedPreferences  =
            requireActivity().getSharedPreferences("localePref" , MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()

        englishCv.setOnClickListener {
            if(!sharedPreferences.getString(LOCALE_KEY,ENGLISH_LOCALE).equals(ENGLISH_LOCALE)){

                languageToolbar.title = "Language"
                englishTv.text = "English"
                uzbekTv.text="Uzbek"

                englishLl.setBackgroundResource(R.color.select_lang_bg_color)
                englishTv.setTextColor(Color.WHITE)

                uzbekLl.setBackgroundResource(R.color.unselect_lang_bg_color)
                uzbekTv.setTextColor(Color.BLACK)

                editor.putString(LOCALE_KEY,ENGLISH_LOCALE)
                editor.apply()

                Lingver.getInstance().setLocale(requireContext(),"en")

            }else{
                val snack = Snackbar.make(requireView(),"You are already in this language!",Snackbar.LENGTH_LONG)
                snack.show()
            }
        }

        uzbekCv.setOnClickListener {
            if(!sharedPreferences.getString(LOCALE_KEY,ENGLISH_LOCALE).equals(UZBEK_LOCALE)){
                uzbekLl.setBackgroundResource(R.color.select_lang_bg_color)
                uzbekTv.setTextColor(Color.WHITE)

                languageToolbar.title = "Til"
                englishTv.text = "Inglizcha"
                uzbekTv.text = "O'zbekcha"

                englishLl.setBackgroundResource(R.color.unselect_lang_bg_color)
                englishTv.setTextColor(Color.BLACK)

                editor.putString(LOCALE_KEY,UZBEK_LOCALE)
                editor.apply()

                Lingver.getInstance().setLocale(requireContext(),"uz")

            }else{
                val snack1 = Snackbar.make(requireView(),"Siz allaqachon shu tildasiz!",Snackbar.LENGTH_LONG)
                snack1.show()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if(sharedPreferences.getString(LOCALE_KEY,ENGLISH_LOCALE).equals(ENGLISH_LOCALE)){
            binding.englishLl.setBackgroundResource(R.color.select_lang_bg_color)
            binding.englishTv.setTextColor(Color.WHITE)

            binding.uzbekLl.setBackgroundResource(R.color.unselect_lang_bg_color)
            binding.uzbekTv.setTextColor(Color.BLACK)
        }else{
            binding.uzbekLl.setBackgroundResource(R.color.select_lang_bg_color)
            binding.uzbekTv.setTextColor(Color.WHITE)


            binding.englishLl.setBackgroundResource(R.color.unselect_lang_bg_color)
            binding.englishTv.setTextColor(Color.BLACK)
        }
    }

}
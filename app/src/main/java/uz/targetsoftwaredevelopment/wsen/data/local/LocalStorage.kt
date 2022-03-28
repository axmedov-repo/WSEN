package uz.targetsoftwaredevelopment.wsen.data.local

import android.content.Context
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.wsen.utils.StringPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor() {
    private val pref = App.instance.getSharedPreferences("UzVolunteers", Context.MODE_PRIVATE)

    var token: String by StringPreference(pref)

    var splashOpenScreen: String by StringPreference(pref, SplashOpenScreenTypes.AUTH_SCREEN.name)

    var userId: String by StringPreference(pref)

    var userPhoneNumber: String by StringPreference(pref)
}
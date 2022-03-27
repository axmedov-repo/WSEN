package uz.targetsoftwaredevelopment.myapplication.data.local

import com.securepreferences.SecurePreferences
import uz.targetsoftwaredevelopment.myapplication.app.App
import uz.targetsoftwaredevelopment.myapplication.utils.StringPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeStorage @Inject constructor() {
    private val KEY = "SHDIJHEUNNSONAIEFIUBOMXss54d5s4d5OSMB4s5456sd4cv8d"
    private val securePref = SecurePreferences(App.instance, KEY, "local_storage.xml")

    var base_url: String by StringPreference(securePref, "http://147.182.250.241/en/")
}
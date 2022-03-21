package uz.targetsoftwaredevelopment.myapplication.data.entities

import android.net.Uri
import java.io.Serializable

data class AdData(
    val videoUrl: Uri? = null
) : Serializable

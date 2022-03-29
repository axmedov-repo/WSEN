package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import java.util.*

data class ErrorRegisterResponse(
    val errorsDictionary: Dictionary<String, List<String>>
)

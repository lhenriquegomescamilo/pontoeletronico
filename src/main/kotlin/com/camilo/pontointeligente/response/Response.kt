package com.camilo.pontointeligente.response

import java.util.*

data class Response<T>(
        val erros: ArrayList<String> = ArrayList(),
        var data: T? = null
)

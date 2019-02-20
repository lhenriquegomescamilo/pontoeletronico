package com.camilo.pontointeligente.response

import java.util.*

data class Response<T>(
        val erros: List<String> = LinkedList(),
        var data: T? = null
)

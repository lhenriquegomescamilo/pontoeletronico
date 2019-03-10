package com.camilo.pontointeligente.response

import java.util.*


data class Response<T>(
        val erros: MutableList<String> = LinkedList(),
        var data: T? = null
) {
    override fun toString(): String {
        return super.toString()
    }
}

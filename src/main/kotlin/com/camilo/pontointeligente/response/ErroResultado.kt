package com.camilo.pontointeligente.response

import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError


fun adicionarErroSeVerdadeiro(condicao: Boolean, resultado: BindingResult, objectError: ObjectError) {
    if (condicao) resultado.addError(objectError)
}
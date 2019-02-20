package com.camilo.pontointeligente.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty

data class FuncionarioDto(
        @get:NotEmpty(message = "Nome não pode ser vazio")
        @get:Length(min = 3, max = 200, message = "Nome deve conte rentre 3 a 200 caracteres")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Length(min = 5, max = 200, message="Email deve conter entre 5 e 200 caracteres.")
        val email: String = "",
        val senha: String? = null,
        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHoraAlmoco: String? = null,
        val id: String? = null
)
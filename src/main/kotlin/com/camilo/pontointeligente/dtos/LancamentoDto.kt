package com.camilo.pontointeligente.dtos

import org.hibernate.validator.constraints.NotEmpty

data class LancamentoDto(
        @get:NotEmpty(message = "Data não pode ser vazio")
        val data: String,

        @get:NotEmpty(message = "Tipo não pode ser vazio")
        val tipo: String,
        val funcionarioId: String,
        val descricao: String? = "",
        val localizacao: String? = "",
        val id: String? = null
)
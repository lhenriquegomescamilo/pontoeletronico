package com.camilo.pontointeligente.dtos

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.enums.TipoEnum
import org.hibernate.validator.constraints.NotEmpty
import java.text.SimpleDateFormat

data class LancamentoDto(
        @get:NotEmpty(message = "Data não pode ser vazio")
        val data: String = "",

        @get:NotEmpty(message = "Tipo não pode ser vazio")
        val tipo: String = "",
        val funcionarioId: String = "",
        val descricao: String? = "",
        val localizacao: String? = "",
        val id: String? = null
) {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        fun toEntity(): Lancamento {
                return Lancamento(
                        dateFormat.parse(data),
                        TipoEnum.valueOf(tipo),
                        funcionarioId,
                        descricao,
                        localizacao,
                        id
                )
        }
}

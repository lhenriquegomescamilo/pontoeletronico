package com.camilo.pontointeligente.documents

import com.camilo.pontointeligente.dtos.LancamentoDto
import com.camilo.pontointeligente.enums.TipoEnum
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import java.text.SimpleDateFormat
import java.util.*

@Document
data class Lancamento (
        val data: Date,
        val tipo: TipoEnum,
        val funcionarioId: String,
        val descricao: String? = "",
        val localizacao: String? = "",
        @Id val id: String? = null
){
    @Transient
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    companion object {
        val lancamentoNaoEncontrado = "Lancamento nao encontrado"
    }

    fun toDto(): LancamentoDto = LancamentoDto(
            dateFormat.format(data),
            tipo.toString(),
            descricao!!,
            localizacao,
            funcionarioId,
            id
    )
}

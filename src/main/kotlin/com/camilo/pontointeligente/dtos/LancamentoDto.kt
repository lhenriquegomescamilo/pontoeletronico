package com.camilo.pontointeligente.dtos

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.enums.TipoEnum
import com.camilo.pontointeligente.response.adicionarErroSeVerdadeiro
import com.camilo.pontointeligente.services.LancamentoService
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import java.text.SimpleDateFormat
import java.util.Objects.isNull
import java.util.Objects.nonNull

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

val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

fun converterLancamentoParaDto(lancamento: Lancamento): LancamentoDto? {
    val (data, tipo, funcionarioId, descricao, localizacao) = lancamento
    return LancamentoDto(dataFormat.format(data), tipo.toString(), funcionarioId, descricao, localizacao)
}

fun converterDtoParaLancamento(
        lancamentoDto: LancamentoDto,
        resultado: BindingResult,
        lancamentoService: LancamentoService
): Lancamento {
    val (data, tipo, funcionarioId, descricao, localizacao, id) = lancamentoDto
    if (nonNull(id)) {
        val lancamentoDoBanco = lancamentoService.buscarPorId(id!!)
        adicionarErroSeVerdadeiro(
                isNull(lancamentoDoBanco),
                resultado,
                ObjectError("lancamento", "lancamento ${lancamentoDto} não encontrado")
        )
    }

    return Lancamento(
            dataFormat.parse(data),
            TipoEnum.valueOf(tipo),
            funcionarioId, descricao, localizacao, id
    )

}
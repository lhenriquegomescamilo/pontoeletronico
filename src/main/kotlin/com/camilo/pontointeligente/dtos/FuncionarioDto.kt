package com.camilo.pontointeligente.dtos

import com.camilo.pontointeligente.response.adicionarErroSeVerdadeiro
import com.camilo.pontointeligente.services.FuncionarioService
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import java.util.*

data class FuncionarioDto(
        @get:NotEmpty(message = "Nome não pode ser vazio")
        @get:Length(min = 3, max = 200, message = "Nome deve conte rentre 3 a 200 caracteres")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
        val email: String = "",
        val senha: String? = null,
        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHoraAlmoco: String? = null,
        val id: String? = null
)

fun validarFuncionario(lancamentoDto: LancamentoDto, resultado: BindingResult, funcionarioService: FuncionarioService) {
    adicionarErroSeVerdadeiro(
            Objects.isNull(lancamentoDto.funcionarioId),
            resultado,
            ObjectError("Funcionario", "Funcionario não foi informado")
    )

    val funcionario = funcionarioService
            .buscarPorId(lancamentoDto.funcionarioId)

    adicionarErroSeVerdadeiro(
            Objects.isNull(funcionario),
            resultado,
            ObjectError("funcionario", "Funcionario ${lancamentoDto.funcionarioId} inexistente")
    )
}
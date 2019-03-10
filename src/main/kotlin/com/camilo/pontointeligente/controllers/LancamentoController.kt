package com.camilo.pontointeligente.controllers

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.dtos.LancamentoDto
import com.camilo.pontointeligente.dtos.converterDtoParaLancamento
import com.camilo.pontointeligente.dtos.converterLancamentoParaDto
import com.camilo.pontointeligente.response.Response
import com.camilo.pontointeligente.response.adicionarErroSeVerdadeiro
import com.camilo.pontointeligente.services.FuncionarioService
import com.camilo.pontointeligente.services.LancamentoService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.ok
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects.isNull
import javax.validation.Valid

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(
        val lancamentoService: LancamentoService,
        val funcionarioService: FuncionarioService
) {

    @PostMapping
    fun adicionar(
            @Valid
            @RequestBody
            lancamentoDto: LancamentoDto,
            resultado: BindingResult
    ): ResponseEntity<Response<LancamentoDto>> {
        val response = Response<LancamentoDto>()

        validarFuncionario(lancamentoDto, resultado)

        if (resultado.hasErrors()) {
            for (error in resultado.allErrors) response.erros.add(error.defaultMessage)
            return badRequest().body(response)
        }

        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, resultado, lancamentoService)
        lancamentoService.persistir(lancamento)
        response.data = converterLancamentoParaDto(lancamento)
        return ok(response)
    }

    private fun validarFuncionario(lancamentoDto: LancamentoDto, resultado: BindingResult) {

        adicionarErroSeVerdadeiro(
                isNull(lancamentoDto.funcionarioId),
                resultado,
                ObjectError("Funcionario", "Funcionario não foi informado")
        )

        val funcionario = funcionarioService
                .buscarPorId(lancamentoDto.funcionarioId)

        adicionarErroSeVerdadeiro(
                isNull(funcionario),
                resultado,
                ObjectError("funcionario", "Funcionario ${lancamentoDto.funcionarioId} inexistente")
        )
    }
}
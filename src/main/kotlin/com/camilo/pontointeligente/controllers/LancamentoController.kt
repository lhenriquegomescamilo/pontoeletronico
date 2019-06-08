package com.camilo.pontointeligente.controllers

import com.camilo.pontointeligente.documents.Funcionario
import com.camilo.pontointeligente.documents.Funcionario.Companion.funcionarioNaoEncontrado
import com.camilo.pontointeligente.documents.Funcionario.Companion.funcionarioNaoEncontradoIdInexistente
import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.dtos.LancamentoDto
import com.camilo.pontointeligente.response.Response
import com.camilo.pontointeligente.services.FuncionarioService
import com.camilo.pontointeligente.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects.isNull
import java.util.Objects.nonNull

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(
        val lancamentoService: LancamentoService,
        val funcionarioService: FuncionarioService
) {
    @Value("\${paginacao.qtd_por_pagina}")
    val quantidadePorPaginas = 15

    @PostMapping
    fun adicionar(
            @RequestBody
            lancamentoDto: LancamentoDto,
            resultBinding: BindingResult
    ): ResponseEntity<Response<LancamentoDto>> {
        val response = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, resultBinding)

        if (resultBinding.hasErrors()) {
            for (erro in resultBinding.allErrors) response.erros.add(erro.defaultMessage)
            return ResponseEntity.badRequest().body(response)
        }
        response.data = lancamentoService.persistir(lancamentoDto.toEntity()).toDto()
        return ResponseEntity.ok(response)

    }

    private fun validarFuncionario(lancamentoDto: LancamentoDto, resultBinding: BindingResult) {
        if (nonNull(lancamentoDto.id)) {
            val lancamento = lancamentoService.buscarPorId(lancamentoDto.id!!)
            if (isNull(lancamento)) resultBinding.addError(ObjectError(Lancamento::class.simpleName, Lancamento.lancamentoNaoEncontrado))
        }
        if (isNull(lancamentoDto.funcionarioId)) {
            resultBinding
                    .addError(ObjectError(Funcionario::class.simpleName, funcionarioNaoEncontrado))
            return
        } else {
            val funcionario = funcionarioService.buscarPorId(lancamentoDto.funcionarioId)
            if (isNull(funcionario)) {
                resultBinding.addError(ObjectError(Funcionario::class.simpleName, funcionarioNaoEncontradoIdInexistente))
            }
        }
    }


}

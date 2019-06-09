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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.util.Objects.isNull
import java.util.Objects.nonNull
import javax.validation.Valid

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

    @GetMapping(value = "/{id}")
    fun listarLancamentoPorId(@PathVariable("id") id: String): ResponseEntity<Response<LancamentoDto>> {
        val response = Response<LancamentoDto>()
        val lancamento = lancamentoService.buscarPorId(id)

        if (isNull(lancamento)) response.erros.add("Lancamento não encontrado para o id : $id")

        response.data = lancamento?.toDto()
        return ResponseEntity.ok(response)
    }

    @GetMapping(value = "/funcionario/{funcionarioId}")
    fun buscarLancamentosPorFuncionarioId(
            @PathVariable("funcionarioId") funcionarioId: String,
            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "order", defaultValue = "id") order: String,
            @RequestParam(value = "dir", defaultValue = "DESC") direciton: String
    ): ResponseEntity<Response<Page<LancamentoDto>>> {
        val response = Response<Page<LancamentoDto>>()
        val pageRequest = PageRequest(page, quantidadePorPaginas, Sort.Direction.valueOf(direciton), order)
        val lancamentos = lancamentoService.buscarPorFuncionarioId(funcionarioId, pageRequest)


        response.data = lancamentos.map { lancamento -> lancamento.toDto() }
        return ResponseEntity.ok(response)

    }

    @PutMapping(value = "/{id}")
    fun atualizarPorId(
            @PathVariable("id") id: String,
            @Valid
            @RequestBody
            lancamentoDto: LancamentoDto,
            resultBinding: BindingResult
    ): ResponseEntity<Response<LancamentoDto>> {
        val response = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, resultBinding)
        if (resultBinding.hasErrors()) {
            for (error in resultBinding.allErrors) response.erros.add(error.defaultMessage)
            return ResponseEntity.badRequest().body(response)
        }
        response.data = lancamentoService.persistir(lancamentoDto.toEntity()).toDto()
        return ResponseEntity.ok(response)

    }

    @DeleteMapping(value = "/{id}")
    fun removerPorId(@PathVariable("id") id: String): ResponseEntity<Response<String>> {
        val lancamento = lancamentoService.buscarPorId(id)
        val response = Response<String>()
        if (isNull(lancamento)) {
            response.erros.add(Lancamento.lancamentoNaoEncontrado)
            return ResponseEntity.badRequest().body(response)
        }
        lancamentoService.removerPorId(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)


    }

}

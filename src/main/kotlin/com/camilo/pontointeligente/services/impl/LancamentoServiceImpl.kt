package com.camilo.pontointeligente.services.impl

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.repositories.LancamentoRepository
import com.camilo.pontointeligente.services.LancamentoService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository) : LancamentoService {
    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest) = lancamentoRepository
            .findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento? = lancamentoRepository.findOne(id)

    override fun persistir(lancamento: Lancamento) = lancamentoRepository.save(lancamento)

    override fun removerPorId(id: String) = lancamentoRepository.delete(id)
}
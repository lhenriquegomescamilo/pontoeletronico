package com.camilo.pontointeligente.services.impl

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.repositories.LancamentoRepository
import com.camilo.pontointeligente.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository) : LancamentoService {
    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buscarPorId(id: String): Lancamento? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun persistir(lancamento: Lancamento) = lancamentoRepository.save(lancamento)

    override fun removerPorId(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
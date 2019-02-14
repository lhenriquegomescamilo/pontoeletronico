package com.pontoeletronico.services.impl

import com.pontoeletronico.documents.Empresa
import com.pontoeletronico.repositories.EmpresaRepository
import com.pontoeletronico.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}
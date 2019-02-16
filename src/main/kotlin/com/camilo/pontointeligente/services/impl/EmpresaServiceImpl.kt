package com.camilo.pontointeligente.services.impl

import com.camilo.pontointeligente.documents.Empresa
import com.camilo.pontointeligente.repositories.EmpresaRepository
import com.camilo.pontointeligente.services.EmpresaService
import org.springframework.stereotype.Service

/**
 * Created by marciosouza on 10/14/17.
 */
@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)

}

package com.camilo.pontointeligente.services

import com.camilo.pontointeligente.documents.Empresa

/**
 * Created by marciosouza on 10/14/17.
 */
interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa

}

package com.camilo.pontointeligente.services

import com.camilo.pontointeligente.documents.Funcionario

/**
 * Created by marciosouza on 10/14/17.
 */
interface FuncionarioService {

    fun persistir(funcionario: Funcionario): Funcionario

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?

}
